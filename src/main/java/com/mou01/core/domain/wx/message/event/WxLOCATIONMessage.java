package com.mou01.core.domain.wx.message.event;

/****
 * 上报地理位置事件<br>
 * 用户同意上报地理位置后，<br>
 * 每次进入公众号会话时，都会在进入时上报地理位置，<br>
 * 或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置。<br>
 * 上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL。 <br>
 * 
 * 推送XML数据包示例：
 * 
 * <pre>
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[LOCATION]]></Event>
 * <Latitude>23.137466</Latitude>
 * <Longitude>113.352425</Longitude>
 * <Precision>119.385040</Precision>
 * </xml>
 * </pre>
 * 
 * @author NBQ
 *
 */
public class WxLOCATIONMessage extends WxEvent {

	private String Latitude;// 地理位置纬度
	private String Longitude;// 地理位置经度
	private String Precision;// 地理位置精度

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}

}
