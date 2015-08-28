package com.mou01.core.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mou.common.JsonUtil;

import com.mou01.core.domain.wx.WxMessage;
import com.mou01.core.domain.wx.WxMessageType;
import com.mou01.core.domain.wx.event.WxLOCATIONMessage;
import com.mou01.core.domain.wx.normal.WxImageMessage;
import com.mou01.core.domain.wx.normal.WxLinkMessage;
import com.mou01.core.domain.wx.normal.WxLocationMessage;
import com.mou01.core.domain.wx.normal.WxNormalMessage;
import com.mou01.core.domain.wx.normal.WxShortvideoMessage;
import com.mou01.core.domain.wx.normal.WxTextMessage;
import com.mou01.core.domain.wx.normal.WxVideoMessage;
import com.mou01.core.domain.wx.normal.WxVoiceMessage;
import com.thoughtworks.xstream.XStream;

public class WxMessageUtil {

	/****
	 * 将微信的请求xml转化为map
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> xml2Map(HttpServletRequest request)
			throws Exception {

		Map<String, String> rtnMap = new HashMap<String, String>();

		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();

		Document doc = reader.read(ins);
		Element root = doc.getRootElement();

		List<Element> list = root.elements();
		for (Element ele : list) {
			rtnMap.put(ele.getName(), ele.getText());
		}

		ins.close();
		return rtnMap;
	}

	public static WxNormalMessage Map2WxNormalMessage(Map<String, String> map) {

		String jsonStr = JsonUtil.toJsonStr(map);
		String MsgType = map.get("MsgType");

		if (MsgType.equals(WxMessageType.MSGTYPE_TEXT)) {
			return JsonUtil.fromJson(jsonStr, WxTextMessage.class);
		} else if (MsgType.equals(WxMessageType.MSGTYPE_VIDEO)) {
			return JsonUtil.fromJson(jsonStr, WxVideoMessage.class);
		} else if (MsgType.equals(WxMessageType.MSGTYPE_VOICE)) {
			return JsonUtil.fromJson(jsonStr, WxVoiceMessage.class);
		} else if (MsgType.equals(WxMessageType.MSGTYPE_SHORTVIDEO)) {
			return JsonUtil.fromJson(jsonStr, WxShortvideoMessage.class);
		} else if (MsgType.equals(WxMessageType.MSGTYPE_LOCATION)) {
			return JsonUtil.fromJson(jsonStr, WxLocationMessage.class);
		} else if (MsgType.equals(WxMessageType.MSGTYPE_LINK)) {
			return JsonUtil.fromJson(jsonStr, WxLinkMessage.class);
		} else if (MsgType.equals(WxMessageType.MSGTYPE_IMAGE)) {
			return JsonUtil.fromJson(jsonStr, WxImageMessage.class);
		} else {
			return null;
		}
	}

	/****
	 * 将文本消息对象转化为xml
	 * 
	 * @param message
	 * @return
	 */
	public static String textMessage2Xml(WxTextMessage message) {
		XStream xstream = new XStream();
		xstream.alias("xml", message.getClass());// 将生成的XML文件的根节点替换成XML，默认为类的全路径类名
		return xstream.toXML(message);
	}
}
