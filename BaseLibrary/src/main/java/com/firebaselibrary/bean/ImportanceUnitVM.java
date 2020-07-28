/**
 * 
 */
package com.firebaselibrary.bean;

import org.creation.common.models.BaseUnit;

import java.util.List;

/**
 * @重点单位
 */

public class ImportanceUnitVM extends BaseUnit {
	/**
	 * @外围水源图
	 */
    private List<String> peripheryImg;
    /**
	 * @外观图
	 */
    private List<String> facadeImg;
    /**
	 * @平面图
	 */
    private List<String> planeImg;
    /**
   	 * @概况图
   	 */
    private List<String> InfoImg;
    /**
   	 * @相关视频
   	 */
    private List<String> vedios;
    /**
   	 * @联系电话
   	 */
    private String phone;

    /**
   	 * @联系人
   	 */
    private String person;

    /**
   	 * @消防负责人
   	 */
    private String FirePrincipal;

    /**
   	 * @占地面积
   	 */
    private String HoldArea;

	/**
	 * =============================================================================================
	 */

	/**
	 * 地址
	 */
	private String address;


	private double x;

	private double y;

	/**
	 * @return the peripheryImg
	 */
	public List<String> getPeripheryImg() {
		return peripheryImg;
	}

	/**
	 * @param peripheryImg the peripheryImg to set
	 */
	public void setPeripheryImg(List<String> peripheryImg) {
		this.peripheryImg = peripheryImg;
	}

	/**
	 * @return the facadeImg
	 */
	public List<String> getFacadeImg() {
		return facadeImg;
	}

	/**
	 * @param facadeImg the facadeImg to set
	 */
	public void setFacadeImg(List<String> facadeImg) {
		this.facadeImg = facadeImg;
	}

	/**
	 * @return the planeImg
	 */
	public List<String> getPlaneImg() {
		return planeImg;
	}

	/**
	 * @param planeImg the planeImg to set
	 */
	public void setPlaneImg(List<String> planeImg) {
		this.planeImg = planeImg;
	}

	/**
	 * @return the infoImg
	 */
	public List<String> getInfoImg() {
		return InfoImg;
	}

	/**
	 * @param infoImg the infoImg to set
	 */
	public void setInfoImg(List<String> infoImg) {
		InfoImg = infoImg;
	}

	/**
	 * @return the vedios
	 */
	public List<String> getVedios() {
		return vedios;
	}

	/**
	 * @param vedios the vedios to set
	 */
	public void setVedios(List<String> vedios) {
		this.vedios = vedios;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the person
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(String person) {
		this.person = person;
	}

	/**
	 * @return the firePrincipal
	 */
	public String getFirePrincipal() {
		return FirePrincipal;
	}

	/**
	 * @param firePrincipal the firePrincipal to set
	 */
	public void setFirePrincipal(String firePrincipal) {
		FirePrincipal = firePrincipal;
	}

	/**
	 * @return the holdArea
	 */
	public String getHoldArea() {
		return HoldArea;
	}

	/**
	 * @param holdArea the holdArea to set
	 */
	public void setHoldArea(String holdArea) {
		HoldArea = holdArea;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
