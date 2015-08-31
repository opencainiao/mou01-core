package com.mou01.core.domain.wx.toweixin;

import java.util.Date;

import org.junit.Test;

import com.mou01.core.domain.wx.message.WxMessageType;
import com.mou01.core.domain.wx.message.toweixin.Music;
import com.mou01.core.domain.wx.message.toweixin.MusicMessage;

public class MusicMessageTest {

	@Test
	public void testToXml() {

		String message = null;
		Music music = new Music();
		music.setThumbMediaId("WsHCQr1ftJQwmGUGhCP8gZ13a77XVg5Ah_uHPHVEAQuRE5FEjn-DsZJzFZqZFeFk");
		music.setTitle("see you again");
		music.setDescription("速7片尾曲");
		music.setMusicUrl("http://zapper.tunnel.mobi/Weixin/resource/See You Again.mp3");
		music.setHQMusicUrl("http://zapper.tunnel.mobi/Weixin/resource/See You Again.mp3");

		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName("11");
		musicMessage.setToUserName("22");
		musicMessage.setMsgType(WxMessageType.MSGTYPE_MUSIC);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMusic(music);
		message = musicMessage.toXML();

		System.out.println(message);
	}
}
