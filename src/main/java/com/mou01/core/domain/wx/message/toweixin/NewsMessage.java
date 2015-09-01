package com.mou01.core.domain.wx.message.toweixin;

import java.util.List;

import com.mou01.core.domain.wx.message.WxMessage;
import com.thoughtworks.xstream.XStream;

/****
 * 向微信返回图文消息
 * 
 * @author NBQ
 *
 */
public class NewsMessage extends WxMessage {

	private int ArticleCount;// 是 图文消息个数，限制为10条以内
	private List<News> Articles;// 是 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<News> getArticles() {
		return Articles;
	}

	public void setArticles(List<News> articles) {
		Articles = articles;
		this.ArticleCount = articles.size();
	}

	@Override
	public String toXML() {
		XStream xstream = getXstream();
		xstream.alias("xml", this.getClass());// 将生成的XML文件的根节点替换成XML，默认为类的全路径类名
		xstream.alias("item", News.class);
		return xstream.toXML(this);
	}

	public static void main(String[] args) {
		System.out.println(new News().getClass());
		System.out.println(News.class);
	}
}
