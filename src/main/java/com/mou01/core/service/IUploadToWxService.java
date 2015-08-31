package com.mou01.core.service;

import com.mou01.core.domain.wx.WxUploadResult;

/****
 * 向微信上传文件
 * 
 * @author NBQ
 *
 */
public interface IUploadToWxService {

	/****
	 * 上传临时素材
	 * 
	 * <pre>
	 * 1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除
	 * （所以用户发送给开发者的素材，若开发者需要，应尽快下载到本地），以节省服务器资源。
	 * 2、media_id是可复用的。
	 * 3、素材的格式大小等要求与公众平台官网一致。具体是，
	 * 图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，
	 * 语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式
	 * 4、需使用https调用本接口。
	 * </pre>
	 * 
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public WxUploadResult uploadTempMediaFile(String filePath,
			String accessToken, String type) throws Exception;

}
