package com.byxxlog.ejb.test;

import java.net.InetAddress;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//import byxx.log.sys.dic_ini;

import com.byxxlog.ejb.delegate.ByxxLogDelegate;
import com.byxxlog.ejb.entity.ByxxLog;
import com.byxxlog.ejb.utils.StaticDate;

public class OpLog {
	public static String  DIC_EJB_SERVICE="t3://localhost:7001/";
	
	public static String getDIC_EJB_SERVICE() {
		return DIC_EJB_SERVICE;
	}

	public static void setDIC_EJB_SERVICE(String dICEJBSERVICE) {
		DIC_EJB_SERVICE = dICEJBSERVICE;
	}

	public static String hostIp;
	public static String hostName;
	public static String[] userPostArr = new String[] { "�ƻ���", "������", "������",
			"������", "�г���", "������", "������", "ʩ����", "ͳ����" };
	public static String[] userIdArr = new String[] { "1001", "2001", "3001",
			"4001", "5001", "6001", "7001", "8001", "9001" };
	public static String[] userNameArr = new String[] { "�ŻƷɺ�", "����", "��ͥ��",
			"�Ÿ���", "������", "����", "����", "�￨��", "������" };
	public static String[] actionTypeArr = new String[] {
			ByxxLog.ACTION_TYPE_LOGIN_, ByxxLog.ACTION_TYPE_OPERATE_ };

	public static String getHostIp() {
		if (hostIp == null) {
			try {
				InetAddress addr = InetAddress.getLocalHost();
				hostName = addr.getHostName().toString();
				hostIp = addr.getHostAddress().toString();
				System.out.println("�������ƣ�" + hostName + "   ip=" + hostIp);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return hostIp;
	}

	public static String getHostName() {
		return hostName;
	}

	public static void initLog() {
		try {
			String sysName = "������Ϣ��ѯ����ƽ̨";
			String userIP = getHostIp();

			Random r = new Random();
			int i = r.nextInt(userPostArr.length);
			String userPost = userPostArr[i];
			String userId = userIdArr[i];
			String userName = userNameArr[i];
			int j = r.nextInt(actionTypeArr.length);
			String actionType = actionTypeArr[j];

			String actionContent = userPost + "," + userId + "" + userName + ""
					+ actionType; 
			ByxxLogDelegate.getInstance().getSession().insert_ByxxLog_Pars(
					userPost, userId, userName, userIP, actionType,
					actionContent, "��", "��", sysName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	static class MyTask extends TimerTask {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("ִ��һ�Σ� "+StaticDate.getNowTime());
			OpLog.initLog();
		}
	}

	public static void main(String[] args) {
		//dic_ini.initServer(); 
		ByxxLogDelegate.url = "t3://127.0.0.1:7001/";// ��־��¼EJB��������� 
		Timer t = new Timer();
		TimerTask task = new MyTask();
			Random r = new Random();
			int i = r.nextInt(100);
			System.out.println("Random i=="+i);
			
		t.schedule(task, 1000,1000*i);

	}

}
