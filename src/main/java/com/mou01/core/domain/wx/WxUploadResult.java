package com.mou01.core.domain.wx;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mou.mongodb.base.domain.BaseModel;

/****
 * 微信上传文件返回结果对象
 * 
 * @author NBQ
 *
 */
@Document(collection = "wx_file_upload")
public class WxUploadResult extends BaseModel {

	private String type;
	private String media_id;
	private String thumb_media_id;
	private String voice_media_id;
	private String video_media_id;
	private String created_at;
	private String errcode;
	private String errmsg;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	public String getVoice_media_id() {
		return voice_media_id;
	}

	public void setVoice_media_id(String voice_media_id) {
		this.voice_media_id = voice_media_id;
	}

	public String getVideo_media_id() {
		return video_media_id;
	}

	public void setVideo_media_id(String video_media_id) {
		this.video_media_id = video_media_id;
	}
}
