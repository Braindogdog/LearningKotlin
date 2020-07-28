package com.firebaselibrary.bean;

import org.creation.common.models.BaseApiQuery;
/**
 * @单位查询参数
 */
@SuppressWarnings("serial")
public class UnitQueryArg extends BaseApiQuery{
	public UnitQueryArg() {
	}
	/**
	 * @名称
	 */
	private String name; 
	/**
	 * @上级
	 */
	private String parentId;
	/**
	 * @单位编号集合【131，323】
	 */
	private String unitIds;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUnitIds() {
		return unitIds;
	}
	public void setUnitIds(String unitIds) {
		this.unitIds = unitIds;
	}
	
}
