package com.mou01.core.domain.wx;

import org.mou.common.JsonUtil;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mou.mongodb.base.domain.BaseModel;

/****
 * 微信的ACCESS_TOKEN
 * 
 * @author NBQ
 *
 */
@Document(collection = "wx_access_token")
public class AccessToken extends BaseModel {

	private String APPID;
	private String APPSECRET;
	private String access_token;// 获取到的凭证
	private int expires_in;// 凭证有效时间，单位：秒
	private int times; // 获取次数

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getAPPID() {
		return APPID;
	}

	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}

	public String getAPPSECRET() {
		return APPSECRET;
	}

	public void setAPPSECRET(String aPPSECRET) {
		APPSECRET = aPPSECRET;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return JsonUtil.getPrettyJsonStr(this);
	}
}
