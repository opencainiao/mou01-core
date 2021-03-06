package com.mou01.core.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;

import org.mou.common.JsonUtil;
import org.mou.common.StringUtil;
import org.springframework.stereotype.Service;

import com.mou.mongodb.base.springdb.dao.CommonDaoMongo;
import com.mou01.core.domain.wx.WxUploadResult;

/****
 * 上传文件至微信服务
 * 
 * @author NBQ
 *
 */
@Service("uploadToWxService")
public class UploadToWxService implements IUploadToWxService {

	@Resource(name = "commonDaoMongo")
	private CommonDaoMongo commonDaoMongo;

	// 提交临时多媒体文件的url
	private static final String UPLOAD_TEMP_MEDIA_FILE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	/****
	 * <pre>
	 * 1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除
	 * （所以用户发送给开发者的素材，若开发者需要，应尽快下载到本地），以节省服务器资源。
	 * 2、media_id是可复用的。
	 * 3、素材的格式大小等要求与公众平台官网一致。具体是，
	 * 图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，
	 * 语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式
	 * 4、需使用https调用本接口。
	 * </pre>
	 */
	@Override
	public WxUploadResult uploadTempMediaFile(String filePath,
			String accessToken, String type) throws Exception {

		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		String url = UPLOAD_TEMP_MEDIA_FILE_URL.replace("ACCESS_TOKEN",
				accessToken).replace("TYPE", type);

		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		System.out.println(result);

		WxUploadResult wxUploadResult = JsonUtil.fromJson(result,
				WxUploadResult.class);

		if (StringUtil.isEmpty(wxUploadResult.getErrcode())) {
			// 上传成功，写入数据库
			wxUploadResult.setCreateInfo();
			commonDaoMongo.insertOne(wxUploadResult);
		}
		return wxUploadResult;
	}
}
