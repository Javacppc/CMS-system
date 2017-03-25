package com.taxsys.core.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 定義一組權限常量：
 * 在這裡通過該類定死了本系統一共有五個系統權限：
 * 行政管理，後勤服務，納稅服務，在線學習，我的空間
 * 所以在本系統中將不會有權限表
 * @author zhuxiaodong
 *
 */
public class Constant {
	//---------------------定義session中保存的用戶記錄------------
	public static String SYS_USER = "SYS_USER";
	//--------------------- 定義用戶權限列表 ---------------------
	public static String PRIVILEGE_XZGL = "xzgl";
	public static String PRIVILEGE_SERVICE = "service";
	public static String PRIVILEGE_NSFW = "nsfw";
	public static String PRIVILEGE_LEARN = "learn";
	public static String PRIVILEGE_SPACE = "space";
	public static Map<String, String> PRIVILEGE_MAP;
	static {
		PRIVILEGE_MAP = new HashMap<>();
		PRIVILEGE_MAP.put(PRIVILEGE_XZGL, "行政管理");
		PRIVILEGE_MAP.put(PRIVILEGE_SERVICE, "後勤服務");
		PRIVILEGE_MAP.put(PRIVILEGE_NSFW, "納稅服務");
		PRIVILEGE_MAP.put(PRIVILEGE_LEARN, "在線學習");
		PRIVILEGE_MAP.put(PRIVILEGE_SPACE, "我的空間");
	}
}
