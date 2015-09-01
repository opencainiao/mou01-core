package com.mou01.core.domain.wx.toweixin;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

import com.mou01.core.domain.wx.message.WxMessage;
import com.mou01.core.domain.wx.message.WxMessageType;
import com.mou01.core.domain.wx.message.normal.WxTextMessage;
import com.thoughtworks.xstream.XStream;

public class TextMessageTest {

	private static final Logger logger = LogManager
			.getLogger(TextMessageTest.class);

	private WxMessage createTextMessage(String ToUserName, String FromUserName,
			String content) {

		WxTextMessage wxTextMessage = new WxTextMessage();
		wxTextMessage.setFromUserName(ToUserName);
		wxTextMessage.setToUserName(FromUserName);
		wxTextMessage.setMsgType(WxMessageType.MSGTYPE_TEXT);
		wxTextMessage.setCreateTime(new Date().getTime());
		wxTextMessage.setContent(content);

		return wxTextMessage;
	}

	private String createLinkMessageContent(String url) {

		String url_to = "<a href=\"" + url + "\">百度" + "</a>";

		String url_to_escaped = HtmlUtils.htmlEscape(url_to);

		logger.debug("url_to\n{}", url_to);
		logger.debug("url_to_escaped\n{}", url_to_escaped);

		return url_to_escaped;
	}

	@Test
	public void testToXml() {

		String url = "http://www.baidu.com";
		String content = createLinkMessageContent(url);

		WxMessage message = createTextMessage("11", "22", content);

		System.out.println(message.toXML());
		
		XStream xstream  = new XStream();
		System.out.println(xstream.toXML(message));
	}

}
