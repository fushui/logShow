package com.byxxlog.ejb.utils;

import java.util.Date;

import com.bea.common.security.xacml.context.Result;

public class TimeUtil {

	public static Integer dateToInt(Date d){
		String d_string = d.toLocaleString();
		String[] ds = d_string.split("-");
		String result = null;
		if(Integer.parseInt(ds[1]) > 0 && Integer.parseInt(ds[1]) < 10){
			result = ds[0] + "0" + ds[1] + ds[2].split(" ")[0];
		}else{
			result = ds[0] + ds[1] + ds[2].split(" ")[0];
		}
		
		//System.out.println("time =  " + result);
		
		return Integer.parseInt(result);
	}
	
	public static void main(String[] args){
		TimeUtil t = new TimeUtil();
		System.out.println(t.dateToInt(new Date()));
	}
}
