package com.mou01.core.service;

import com.mou01.core.domain.wx.AccessToken;

public interface IAccessTokenService {

	public AccessToken getAccessToken();
	
	public AccessToken getAccessToken(String appid,String appsecret);
}
