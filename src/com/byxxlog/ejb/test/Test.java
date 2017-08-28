package com.byxxlog.ejb.test;

import java.util.List;

import com.byxxlog.ejb.delegate.ByxxLogDelegate;
import com.byxxlog.ejb.entity.ByxxLog;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ByxxLogDelegate.url = "t3://127.0.0.1:7001/";// 日志记录EJB部署服务器
		try {
		 
			// 查询所有日志
			List<ByxxLog> li = ByxxLogDelegate.getInstance().getSession()
					.findByxxLogAll();
			System.out.println("findByxxLogAll size==" + li.size()); 
			
			for(int i = 0; i < li.size(); i ++){
				ByxxLog log = li.get(i);
				System.out.println("时间为： " + log.getCreateTime() + "登陆类型为： " + log.getActionType());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

