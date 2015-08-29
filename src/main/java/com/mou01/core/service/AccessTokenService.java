package com.mou01.core.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.DateUtil;
import org.mou.common.JsonUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mou.mongodb.base.springdb.dao.CommonDaoMongo;
import com.mou01.core.domain.wx.AccessToken;
import com.mou01.core.util.CommonDomainInfoSetter;
import com.mou01.core.util.HttpClientUtilWx;

/****
 * 获取AccessToken
 * 
 * @author NBQ
 *
 */
@Service("accessTokenService")
public class AccessTokenService implements IAccessTokenService {

	public static final String APPID = "wx8758b4615fb3bf3b";
	public static final String APPSECRET = "f395c9ab615ae8ea647d840f360d9d2e";
	public static final String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static final Logger logger = LogManager.getLogger(HttpClientUtilWx.class);

	@Resource(name = "commonDaoMongo")
	private CommonDaoMongo commonDaoMongo;

	@Override
	public AccessToken getAccessToken() {
		return getAccessToken(APPID, APPSECRET);
	}

	@Override
	public AccessToken getAccessToken(String appid, String appsecret) {

		AccessToken accessToken = null;

		boolean isExist = false;
		String _id = null;

		// 1. 从全局配置中取ACCESSTOKEN
		Criteria criteria = Criteria.where("APPID").is(APPID);
		criteria.and("APPSECRET").is(APPSECRET);
		Query query = new Query(criteria);
		accessToken = commonDaoMongo.findOne(query, AccessToken.class);

		logger.debug("数据库中的ACCESS_TOKEN\n", accessToken);

		if (accessToken != null) {
			isExist = true;
			_id = accessToken.get_id_m();

			// 如果没超时，直接返回
			String last_op_time = accessToken.getLast_op_time();
			String now = DateUtil.getCurrentTimsmp();

			logger.debug("\n{}\n{}", last_op_time, now);
			int between_seconds = DateUtil.getIntervalTimeStampSecond(now, last_op_time);
			logger.debug("距上次获取时间差[{}]秒", between_seconds);

			int expires_in = accessToken.getExpires_in() - 200;

			if (between_seconds <= expires_in) {
				return accessToken;
			}
		}

		// 2.远程获取AccessToken
		String tokenUrl = getTokenUrl.replace("APPID", APPID).replace("APPSECRET", APPSECRET);

		Map<String, Object> result = HttpClientUtilWx.doGet(tokenUrl);
		if (result == null) {
			return null;
		}

		accessToken = JsonUtil.fromJson(JsonUtil.toJsonStr(result), AccessToken.class);

		// 3.设置到全局数据库中

		if (isExist) {
			// 更新
			DBObject update = new BasicDBObject();
			DBObject updateSet = new BasicDBObject();
			updateSet.put("access_token", accessToken.getAccess_token());
			updateSet.put("expires_in", accessToken.getExpires_in());
			CommonDomainInfoSetter.setModifyInfo(updateSet);
			update.put("$set", updateSet);
			updateSet.put("$inc", new BasicDBObject("times", 1));

			DBObject upResult = commonDaoMongo.updateOneById(_id, null, update, AccessToken.class);
			logger.info("更新成功，更新后的结果\n[{}]", JsonUtil.getPrettyJsonStr(upResult));

		} else {
			// 插入
			accessToken.setAPPID(APPID);
			accessToken.setAPPSECRET(APPSECRET);
			accessToken.setTimes(1);

			CommonDomainInfoSetter.setCreateInfo(accessToken);

			_id = commonDaoMongo.insertOne(accessToken);

			logger.info("首次获取token，插入数据库成功！\n_id[{}]", _id);
		}

		return accessToken;
	}

}
