package com.byxxlog.ejb.ui;

public class LogType {

	private int error_num;
	private int login_num;
	private int operate_num;

	
	public LogType(int error_num, int login_num, int operate_num) {
		super();
		this.error_num = error_num;
		this.login_num = login_num;
		this.operate_num = operate_num;
	}

	public LogType() {
		super();
	}
	
	public int getError_num() {
		return error_num;
	}

	public void setError_num(int error_num) {
		this.error_num = error_num;
	}

	public int getLogin_num() {
		return login_num;
	}

	public void setLogin_num(int login_num) {
		this.login_num = login_num;
	}

	public int getOperate_num() {
		return operate_num;
	}

	public void setOperate_num(int operate_num) {
		this.operate_num = operate_num;
	}

	@Override
	public String toString() {
		return "LogType [error_num=" + error_num + ", login_num=" + login_num + ", operate_num=" + operate_num + "]";
	}

	

}
