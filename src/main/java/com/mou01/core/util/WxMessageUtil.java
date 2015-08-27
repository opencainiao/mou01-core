package com.mou01.core.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mou01.core.domain.WxTextMessage;
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
