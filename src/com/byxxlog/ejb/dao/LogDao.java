package com.byxxlog.ejb.dao;

import java.util.List;

import com.byxxlog.ejb.delegate.ByxxLogDelegate;
import com.byxxlog.ejb.entity.ByxxLog;

public class LogDao {

	public List<ByxxLog> getData() {
		ByxxLogDelegate.url = "t3://127.0.0.1:7001/";// 日志记录EJB部署服务器

		List<ByxxLog> li = null;
		try {
			li = ByxxLogDelegate.getInstance().getSession().findByxxLogAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}
}
