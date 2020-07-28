package com.firebaselibrary.bean;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class AppInfoVM  implements Serializable{
	public  AppInfoVM(String id,String name,String pack,String start,List<String> images) {
		this.id=id;
		this.name=name;
		this.packageName=pack;
		this.start=start;
		this.images=images;
	}
    private String id;
	/**
	 * @名称
	 */
	private String name="暂无";
	/**
	 * @版本号
	 */
	private double version;
	/**
	 * @升级地址
	 */
	private String upHttpUrl;
	/**
	 * @图标集
	 */
	private List<String> images;
	/**
	 * @包名
	 */
	private String packageName;
	/**
	 * @起始页
	 */
	private String start;
	/**
	 * 版本号
	 */
	private String versionName;
	/**
	 * 版本号
	 */
	private String description;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
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
	 * @return the images
	 */
	public List<String> getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(List<String> images) {
		this.images = images;
	}
	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}
	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @return the version
	 */
	public double getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(double version) {
		this.version = version;
	}
	/**
	 * @return the upHttpUrl
	 */
	public String getUpHttpUrl() {
		return upHttpUrl;
	}
	/**
	 * @param upHttpUrl the upHttpUrl to set
	 */
	public void setUpHttpUrl(String upHttpUrl) {
		this.upHttpUrl = upHttpUrl;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	


	
}
