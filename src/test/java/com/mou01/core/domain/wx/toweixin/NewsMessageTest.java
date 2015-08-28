package com.mou01.core.domain.wx.toweixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.mou01.core.domain.wx.WxMessage;
import com.mou01.core.domain.wx.WxMessageType;

public class NewsMessageTest {

	
	public News createNews() {
		News news = new News();
		news.setTitle("慕课网介绍");
		news.setPicUrl("http://img1.imgtn.bdimg.com/it/u=4061686576,439297045&fm=21&gp=0.jpg");
		news.setUrl("http://www.imooc.com/");
		news.setDescription("专注做好IT技能教育的MOOC，符合互联网发展潮流接地气儿的MOOC。我们免费，我们只教有用的，我们专心做教育。");
		return news;
	}
	
	public NewsMessage createNewsMessage(){
		NewsMessage newsMessage = new NewsMessage();
		
		newsMessage.setToUserName("aa");
		newsMessage.setFromUserName("bb");
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WxMessageType.MSGTYPE_NEWS);
		
		List<News> newsList = new ArrayList<News>();
		newsList.add(createNews());
		
		newsMessage.setArticles(newsList);
		
		return newsMessage;
	}
	
	@Test
	public void testSendNewsMessage(){
		WxMessage message = createNewsMessage();
		
		System.out.println(message.toXML());
	}

}
