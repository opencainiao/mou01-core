package com.mou01.core.domain.wx.toweixin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.mou01.core.domain.wx.AccessToken;
import com.mou01.core.util.HttpClientUtilWx;

public class HttpClientUtilWxTest {
	private static final Logger logger = LogManager.getLogger(HttpClientUtilWxTest.class);

	@Test
	public void getToken() {

		AccessToken token = HttpClientUtilWx.getAccessToken();

		logger.debug(token);
	}
}
