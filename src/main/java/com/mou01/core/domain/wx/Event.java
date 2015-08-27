package com.mou01.core.domain.wx;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mou.mongodb.base.domain.BaseModel;

@Document(collection = "event_log")
public class Event extends BaseModel {

	private String ToUserName;
	private String FromUserName;
	private String MsgType;
	private String CreateTime;
	private String Event;
	private String event_name;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;

		if (event.equals("unsubscribe")) {
			this.event_name = "取消关注";
		} else if (event.equals("subscribe")) {
			this.event_name = "关注";
		}
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public void SetInfoSubOrUnSub(Map<String, String> paramsMap) {

		this.setToUserName(paramsMap.get("ToUserName"));
		this.setFromUserName(paramsMap.get("FromUserName"));
		this.setCreateTime(paramsMap.get("CreateTime"));
		this.setEvent(paramsMap.get("Event"));
		this.setMsgType(paramsMap.get("MsgType"));

	}

}
