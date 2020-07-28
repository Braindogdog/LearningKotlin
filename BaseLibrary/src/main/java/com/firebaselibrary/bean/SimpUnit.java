package com.firebaselibrary.bean;

import org.creation.common.models.BaseUnit;

/**
 * @单位信息
 */
@SuppressWarnings("serial")
public class SimpUnit extends BaseUnit {
	/**
	 * @上级单位
	 */
	private BaseUnit parent;
	/**
	 * @附加属性
	 */
	private String targetParent;
	/**
	 * @排序
	 */
	private String sequence;
	/**
	 * @人员数量
	 */
	private int personCount;
	/**
	 * @单位地址
	 */
	private String address;

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public BaseUnit getParent() {
		return parent;
	}

	public void setParent(BaseUnit parent) {
		this.parent = parent;
	}

	public String getTargetParent() {
		return targetParent;
	}

	public void setTargetParent(String targetParent) {
		this.targetParent = targetParent;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

}
