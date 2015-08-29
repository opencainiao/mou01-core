package com.mou01.core.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.DateUtil;
import org.mou.common.JsonUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mou.mongodb.base.springdb.dao.CommonDaoMongo;
import com.mou01.core.domain.wx.AccessToken;

/****
 * 微信使用的HttpClient
 * 
 * @author NBQ
 *
 */
public class HttpClientUtilWx {

	public static final String APPID = "wx8758b4615fb3bf3b";
	public static final String APPSECRET = "f395c9ab615ae8ea647d840f360d9d2e";
	public static final String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static final Logger logger = LogManager.getLogger(HttpClientUtilWx.class);

	public static Map<String, Object> doGet(String url) {

		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet httpGet = new HttpGet(url);

		Map<String, Object> rtnMap = null;
		try {
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 响应状态
			logger.debug("status:{}", httpResponse.getStatusLine());
			// 判断响应实体是否为空
			if (entity != null) {

				String responseStr = EntityUtils.toString(entity, "UTF-8");
				logger.debug("response content\n{}", responseStr);

				rtnMap = JsonUtil.fromJson(responseStr, Map.class);

				logger.debug("返回的Map：\n{}", rtnMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return rtnMap;
	}

	public static Map<String, Object> doPostToWx(String url, String outstr) {

		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpPost httpPost = new HttpPost(url);

		Map<String, Object> rtnMap = null;
		try {
			httpPost.setEntity(new StringEntity(outstr, "UTF-8"));
			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 响应状态
			logger.debug("status:{}", httpResponse.getStatusLine());
			// 判断响应实体是否为空
			if (entity != null) {
				logger.debug("contentEncoding:" + entity.getContentEncoding());
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				logger.debug("response content\n", responseStr);

				rtnMap = JsonUtil.fromJson(responseStr, Map.class);

				logger.debug("返回的Map：\n{}", rtnMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return rtnMap;
	}

	public static AccessToken getAccessToken() {

		AccessToken accessToken = null;

		boolean isExist = false;

		// 1. 从全局配置中取ACCESSTOKEN
		String _id = null;
		CommonDaoMongo commonDaoMongo = new CommonDaoMongo();
		Criteria criteria = Criteria.where("APPID").is(APPID);
		criteria.and("APPSECRET").is(APPSECRET);
		Query query = new Query(criteria);
		accessToken = commonDaoMongo.findOne(query, AccessToken.class);

		logger.debug("数据库中的ACCESS_TOKEN\n{}", accessToken);

		if (accessToken != null) {
			isExist = true;
			_id = accessToken.get_id_m();

			String last_op_time = accessToken.getLast_op_time();
			String now = DateUtil.getCurrentTimsmp();

			logger.debug("\n{}\n{}", last_op_time, now);
			int between_seconds = DateUtil.getIntervalTimeStampSecond(now, last_op_time);
			logger.debug("距上次获取时间差[{}]秒", between_seconds);

			int expires_in = accessToken.getExpires_in() - 200;
			logger.debug("expires_in[{}]",expires_in);

			if (between_seconds <= expires_in) {
				return accessToken;
			}
		}

		// 2.远程获取AccessToken
		String tokenUrl = getTokenUrl.replace("APPID", APPID).replace("APPSECRET", APPSECRET);

		Map<String, Object> result = doGet(tokenUrl);
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
			update.put("$inc", new BasicDBObject("times", 1));

			DBObject upResult = commonDaoMongo.updateOneById(_id, null, update, AccessToken.class);
			logger.info("更新成功，更新后的结果[{}]", JsonUtil.getPrettyJsonStr(upResult));
		} else {
			// 插入
			accessToken.setAPPID(APPID);
			accessToken.setAPPSECRET(APPSECRET);
			accessToken.setTimes(1);
			CommonDomainInfoSetter.setCreateInfo(accessToken);

			_id = commonDaoMongo.insertOne(accessToken);

			logger.info("首次获取token，插入数据库成功！_id[{}]", _id);
		}

		return accessToken;
	}
}
