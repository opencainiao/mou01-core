package com.mou01.core.domain.wx.message.toweixin;

import com.mou01.core.domain.wx.message.WxMessage;

public class MusicMessage extends WxMessage {
	
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
