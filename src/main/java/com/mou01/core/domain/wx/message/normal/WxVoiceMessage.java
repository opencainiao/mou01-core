package com.mou01.core.domain.wx.message.normal;


public class WxVoiceMessage extends WxNormalMessage {

	private String MediaId;// 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String Format;// 语音格式，如amr，speex等
	private String Recongnition; // 语音识别内容

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecongnition() {
		return Recongnition;
	}

	public void setRecongnition(String recongnition) {
		Recongnition = recongnition;
	}

}
