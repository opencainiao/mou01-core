package com.mou01.core.util;

import org.bson.types.ObjectId;
import org.mou.common.StringUtil;

public class ValidateUtil {

	/****
	 * 判断是否合法的objectId
	 * 
	 * @param _id
	 * @return
	 */
	public static boolean isValidObjId(String _id) {

		if (StringUtil.isEmpty(_id)) {
			return false;
		}

		return ObjectId.isValid(_id);
	}

}
