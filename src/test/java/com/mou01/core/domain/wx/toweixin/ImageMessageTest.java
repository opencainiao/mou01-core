package com.mou01.core.domain.wx.toweixin;

import java.util.Date;

import org.junit.Test;

import com.mou01.core.domain.wx.message.WxMessageType;
import com.mou01.core.domain.wx.message.toweixin.Image;
import com.mou01.core.domain.wx.message.toweixin.ImageMessage;

public class ImageMessageTest {

	@Test
	public void testtoXML() {

		ImageMessage im = new ImageMessage();

		im.setFromUserName("11");
		im.setToUserName("22");
		im.setMsgType(WxMessageType.MSGTYPE_IMAGE);
		Image image = new Image();
		image.setMediaId("1231321o3u21uisauoewqjewqdjoaweuoiwq");
		im.setImage(image);
		im.setCreateTime(new Date().getTime());

		System.out.println(im.toXML());
	}

}
