package com.mou01.core.domain.wx.message.normal;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mou01.core.domain.wx.message.WxMessage;

/****
 * 普通消息
 * 
 * @author NBQ
 *
 */
@Document(collection = "wx_message")
public class WxNormalMessage extends WxMessage {

	protected String MsgId;// 消息id，64位整型

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}
