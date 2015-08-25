package com.mou01.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/****
 * 微信Check签名帮助类
 * 
 * @author NBQ
 *
 */
public class CheckUtilWx {

	private static final String token = "moumou";

	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		try {

			for (String str : arr) {
				System.out.print(str);
			}
			Arrays.sort(arr);

			StringBuffer sb = new StringBuffer();
			for (String str : arr) {
				sb.append(str);
			}

			String content = sb.toString();

			String shastr = encrypt(content);
			return shastr.equals(signature);
		} catch (Exception e) {
			e.printStackTrace();

			for (String str : arr) {
				System.out.print(str);
			}
			return false;
		}
	}

	/**
	 * 将字节数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuilder sbDes = new StringBuilder();
		String tmp = null;
		for (int i = 0; i < b.length; i++) {
			tmp = (Integer.toHexString(b[i] & 0xFF));
			if (tmp.length() == 1) {
				sbDes.append("0");
			}
			sbDes.append(tmp);
		}
		return sbDes.toString();
	}

	private static String encrypt(String strSrc)
			throws NoSuchAlgorithmException {

		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] bt = strSrc.getBytes();
		digest.update(bt);
		String strDes = byte2hex(digest.digest());
		return strDes;
	}

}
