package com.mou01.core.domain.wx.message.toweixin;


/****
 * 向微信返回图片消息
 * 
 * @author NBQ
 *
 */
public class Image {

	private String MediaId;// 通过上传多媒体文件，得到的id

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
