package com.firebaselibrary.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @App应用盒子
 */
@SuppressWarnings("serial")
public class AppBoxVM implements Serializable {
	public AppBoxVM(String stype) {
		this.stype = stype;
	}

	/**
	 * @分类
	 */
	private String stype;
	/**
	 * @应用
	 */
	private List<AppInfoVM> apps = new ArrayList<AppInfoVM>();
	/**
	 * @排序
	 */
	private int sequence;

	/**
	 * @return the stype
	 */
	public String getStype() {
		return stype;
	}

	/**
	 * @param stype
	 *            the stype to set
	 */
	public void setStype(String stype) {
		this.stype = stype;
	}

	/**
	 * @return the apps
	 */
	public List<AppInfoVM> getApps() {
		return apps;
	}

	/**
	 * @param apps
	 *            the apps to set
	 */
	public void setApps(List<AppInfoVM> apps) {
		this.apps = apps;
	}

	/**
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence
	 *            the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
