package com.mou01.core.domain.wx;

import com.mou.mongodb.base.domain.BaseModel;

public class WxMessage extends BaseModel{

	protected String ToUserName;// 开发者微信号
	protected String FromUserName;// 发送方帐号（一个OpenID）
	protected String CreateTime;// 消息创建时间 （整型）
	protected String MsgType; // text

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

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
