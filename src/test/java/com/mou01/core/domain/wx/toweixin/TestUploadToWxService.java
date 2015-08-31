package com.mou01.core.domain.wx.toweixin;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.mou01.core.domain.wx.AccessToken;
import com.mou01.core.domain.wx.WxUploadResult;
import com.mou01.core.service.IUploadToWxService;
import com.mou01.core.service.UploadToWxService;
import com.mou01.core.util.HttpClientUtilWx;

public class TestUploadToWxService {

	private static final Logger logger = LogManager
			.getLogger(TestUploadToWxService.class);

	@Test
	public void testUpload() throws IllegalAccessException, InvocationTargetException {

		AccessToken token = HttpClientUtilWx.getAccessTokenTestWxH();
		logger.debug("获取的AccessToken\n[{}]", token);

		String path = "G:/pic/kl.jpg";

		IUploadToWxService uploadToWxService = new UploadToWxService();

		try {
			WxUploadResult wxUploadResult = uploadToWxService.uploadTempMediaFile(path,
					token.getAccess_token(), "thumb");
			

			logger.debug("上传文件至微信的返回结果\n[{}]", wxUploadResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
