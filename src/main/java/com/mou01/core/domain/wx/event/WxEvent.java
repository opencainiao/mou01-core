package com.mou01.core.domain.wx.event;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mou01.core.domain.wx.WxMessage;

@Document(collection = "wx_event")
public class WxEvent extends WxMessage {

	protected String Event;

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public void SetInfoSubOrUnSub(Map<String, String> paramsMap) {

		this.setToUserName(paramsMap.get("ToUserName"));
		this.setFromUserName(paramsMap.get("FromUserName"));
		this.setCreateTime(paramsMap.get("CreateTime"));
		this.setEvent(paramsMap.get("Event"));
		this.setMsgType(paramsMap.get("MsgType"));
	}

}
