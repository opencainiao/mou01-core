package com.mou01.core.domain.wx.event;

/****
 * 点击菜单跳转链接时的事件推送<br>
 * 
 * 
 * 推送XML数据包示例：
 * 
 * <pre>
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[VIEW]]></Event>
 * <EventKey><![CDATA[www.qq.com]]></EventKey>
 * </xml>
 * </pre>
 * 
 * @author NBQ
 *
 */
public class WxVIEWMessage extends WxEvent {

	private String EventKey;// 事件KEY值，设置的跳转URL

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
