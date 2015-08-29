package com.mou01.core.util;

import org.mou.common.DateUtil;

import com.mongodb.DBObject;
import com.mou.mongodb.base.domain.BaseModel;

/****
 * 通用域模型的属性设置器
 * 
 * @author NBQ
 *
 */
public class CommonDomainInfoSetter {

	/****
	 * 设置对象创建信息
	 * 
	 * @param model
	 */
	public static void setCreateInfo(BaseModel model) {
		String date = DateUtil.getCurdate();
		String time = DateUtil.getCurrentTimsmp();

		model.setC_time(time);
		model.setC_date(date);
		model.setDel_flg(false);
		model.setLast_op_time(time);
		model.setLast_op_date(date);
	}
	
	/****
	 * 设置更新信息
	 */
	public static void setModifyInfo(DBObject dbObject) {

		dbObject.put("last_op_date", DateUtil.getCurdate());
		dbObject.put("last_op_time", DateUtil.getCurrentTimsmp());
	}
}
