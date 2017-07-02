package com.apus.pink.hole.commons.constant;

/**
 * 操作系统名称Enum
 * Created by wangmaojun on 2017-05-19.
 */
public enum OSNameEnum {
	WINDOWS,
	LINUX,
	OTHER;
	
	public static OSNameEnum getOSName(String osName){
		if(null == osName || osName.trim().length() == 0){
			return OSNameEnum.OTHER;
		}
		
		String lowerName = osName.toLowerCase();
		
		if(lowerName.startsWith("win")){
			return OSNameEnum.WINDOWS;
		}else if(lowerName.startsWith("lin")){
			return OSNameEnum.LINUX;
		}else{
			return OSNameEnum.OTHER;
		}
	}
	
}
