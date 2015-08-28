package com.mou01.core.domain.wx.normal;


public class WxImageMessage extends WxNormalMessage {

	private String PicUrl; // 图片链接
	private String MediaId;// 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
