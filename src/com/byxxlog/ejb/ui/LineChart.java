package com.byxxlog.ejb.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import com.byxxlog.ejb.dao.LogDao;
import com.byxxlog.ejb.entity.ByxxLog;

public class LineChart {

	public static final int FLAG_FIRST = 1;
	public static final int FLAG_SECOND = 2;
	public static final int FLAG_THIRD = 3;

	public static final String ACTION_TYPE_ERROR = "异常";
	public static final String ACTION_TYPE_LOGIN = "登录";
	public static final String ACTION_TYPE_OPERATE = "操作";


	Map<Integer,LogType> typeMapResult = null;
	
	
    

	private Integer day;
	public LineChart(){
		
	}
	
	public LineChart(Integer day){
		this.day = day;
	}
	public boolean initData() {
		System.out.println("initData......");
		LogDao dao = new LogDao();
		List<ByxxLog> li = dao.getData();
		System.out.println("size: " + li.size());
		
		Map<Integer, List<ByxxLog>> map = new HashMap<Integer, List<ByxxLog>>();

		map = classifyByDate(li);

		for (Map.Entry<Integer, List<ByxxLog>> entry : map.entrySet()) {
			Integer day = entry.getKey();
			
			
			System.out.println("Key = " + entry.getKey() + ", ValueSize = " + entry.getValue().size());
			// List<ByxxLog> logList = entry.getValue();
			// for(ByxxLog log : logList){
			//
			// }

			Map<Integer, List<ByxxLog>> timemap = classifyByTime(entry.getValue());
//			for (Map.Entry<Integer, List<ByxxLog>> timeEntry : timemap.entrySet()) {
//
//				System.out
//						.println("key[time] = " + timeEntry.getKey() + "value[time] = " + timeEntry.getValue().size());
//			}
			
			Map<Integer,LogType> typeMap = classifyByType(timemap);
			for(Map.Entry<Integer, LogType> typeEntry: typeMap.entrySet()){
				Integer key = typeEntry.getKey();
				LogType logType = typeEntry.getValue();
				System.out.println("key = " + key + "value = " + logType.toString());
			} 
			
			
            if(this.day.equals(day)){
            	this.typeMapResult = typeMap;
            	return true;
            	//drawChart(typeMap, day);
			}
			

		}
		
		
		return false;
	}

	private void drawPanel() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "该日期没有数据...", "提示", JOptionPane.ERROR_MESSAGE);
	}

	public void mapPutList(Map<Integer, List<ByxxLog>> map, Integer key, ByxxLog log) {

		List<ByxxLog> value = map.get(key);

		if (null == value) {
			value = new ArrayList<ByxxLog>();
			map.put(key, value);
		}
		value.add(log);
		map.put(key, value);

	}

	// 按日期分类
	public Map<Integer, List<ByxxLog>> classifyByDate(List<ByxxLog> li) {
		List<Integer> time_lists = new ArrayList<Integer>();
		Map<Integer, List<ByxxLog>> map = new HashMap<Integer, List<ByxxLog>>();

		for (ByxxLog log : li) {
			String dayString = log.getCreateTime().split(" ")[0];
			Integer day = Integer.parseInt(dayString);
			mapPutList(map, day, log);
		}

		return map;
	}

	// 继续用时间段分类
	public Map<Integer, List<ByxxLog>> classifyByTime(List<ByxxLog> li) {

		Map<Integer, List<ByxxLog>> map = new HashMap<Integer, List<ByxxLog>>();

		for (ByxxLog log : li) {
			String createTimeStr = log.getCreateTime();
			String[] createTimTmpArray = createTimeStr.split(":")[0].split(" ");
			Integer createTimeInt = Integer.parseInt(createTimTmpArray[0]) * 100
					+ Integer.parseInt(createTimTmpArray[1]);
			//createTimeInt = createTimeInt / 100 * 100 + (getFlag(createTimeInt) - 1) * 8;
			// System.out.println("createTimeInt" + createTimeInt);
			mapPutList(map, createTimeInt, log);
		}

		return map;
	}

	public void mapPut(Map<Integer, Integer> map, Integer key, Integer value) {
		if (null == map.get(key)) {
			map.put(key, 0);
		}
		map.put(key, map.get(key) + value);

	}

	public Map<Integer,LogType> classifyByType(Map<Integer,List<ByxxLog>> timeMap){
		Map<Integer,LogType> map = new HashMap<Integer,LogType>();

		for(Map.Entry<Integer, List<ByxxLog>> entry: timeMap.entrySet()){
			Integer timeInt = entry.getKey();
			List<ByxxLog> logList = entry.getValue();
			LogType logType = new LogType();
			
			for(ByxxLog log: logList){
			  String type = log.getActionType();
			  if(ACTION_TYPE_LOGIN.equals(type)){
				  logType.setLogin_num(logType.getLogin_num() + 1);
			  }else if(ACTION_TYPE_OPERATE.equals(type)){
				  logType.setOperate_num(logType.getOperate_num() + 1);
			  }else if(ACTION_TYPE_ERROR.equals(type)){
				  logType.setError_num(logType.getError_num() + 1);
			  }
			}
			
			map.put(timeInt, logType);
			
		}
		return map;
	}

	
	
	public void draw(boolean flag){
		if(flag){
			//JFrame jf_main = new JFrame();
			JFrame jf = new JFrame();
			jf.add(getChart(this.typeMapResult, this.day));
		    jf.setBounds(100, 100, 700, 500);	
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jf.setVisible(true);
			//RefineryUtilities.centerFrameOnScreen(jf);
			
		}else{
			drawPanel();
		}
	}

	public Integer getFlag(Integer time) {
		int timeTmp = time % 100;
		int flag = 0;
		if (timeTmp >= 0 && timeTmp < 8) {
			flag = FLAG_FIRST;
		} else if (timeTmp >= 8 && timeTmp < 16) {
			flag = FLAG_SECOND;
		} else if (timeTmp >= 16 && timeTmp < 24) {
			flag = FLAG_THIRD;
		}

		return flag;
	}

	

	
	public JPanel getChart(Map<Integer,LogType> map, Integer day){
	      LineChart_S chart = new LineChart_S(map ,
	      "事件类型折线图" ,
	      day + "");

	      //chart.pack( );
	      //RefineryUtilities.centerFrameOnScreen( chart );
	      //chart.setVisible( true );
	      
	      //chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  return chart.getChart("");
	}
	public static void main(String[] args) {
		LineChart chart = new LineChart(20170817);
		boolean flag = chart.initData();
		chart.draw(flag);

	}
	
	
	
	
	public Map<Integer, LogType> getTypeMapResult() {
		return typeMapResult;
	}

	public void setTypeMapResult(Map<Integer, LogType> typeMapResult) {
		this.typeMapResult = typeMapResult;
	}

}
