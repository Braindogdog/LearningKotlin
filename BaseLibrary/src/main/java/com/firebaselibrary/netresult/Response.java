package com.firebaselibrary.netresult;

import com.google.gson.annotations.SerializedName;

public class Response {
	@SerializedName("code")
	public String code;
	@SerializedName("desc")
	public String description;
	@SerializedName("ts")
	public long timestamp;

	public boolean isSuccess() {
		return "200".equals(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
