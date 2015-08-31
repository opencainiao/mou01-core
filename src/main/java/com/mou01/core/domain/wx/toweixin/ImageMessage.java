package com.mou01.core.domain.wx.toweixin;

import com.mou01.core.domain.wx.WxMessage;

/****
 * 向微信返回图片消息
 * 
 * @author NBQ
 *
 */
public class ImageMessage extends WxMessage {

	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

}
