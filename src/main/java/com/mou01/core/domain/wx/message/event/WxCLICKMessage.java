package com.mou01.core.domain.wx.message.event;

/****
 * 自定义菜单事件<br>
 * 用户点击自定义菜单后，微信会把点击事件推送给开发者，<br>
 * 请注意，点击菜单弹出子菜单，不会产生上报。 <br>
 * 
 * 推送XML数据包示例：
 * 
 * <pre>
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[CLICK]]></Event>
 * <EventKey><![CDATA[EVENTKEY]]></EventKey>
 * </xml>
 * 
 * @author NBQ
 *
 */
public class WxCLICKMessage extends WxEvent {

	private String EventKey;// 事件KEY值，与自定义菜单接口中KEY值对应

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
