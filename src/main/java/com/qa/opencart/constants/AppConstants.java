package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	//Configuration properties files path
	public static final String CONFIG_QA_FILE_PATH = "./src/test/resources/config/qa.properties";
	public static final String CONFIG_DEV_FILE_PATH = "./src/test/resources/config/dev.properties";
	public static final String CONFIG_STAGE_FILE_PATH = "./src/test/resources/config/stage.properties";
	public static final String CONFIG_UAT_FILE_PATH = "./src/test/resources/config/uat.properties";
	public static final String CONFIG_PROD_FILE_PATH = "./src/test/resources/config/config.properties";
	
	// Test data sheet names
	public static final String REG_SHEET_NAME = "register";
	public static final String SEARCH_SHEET_NAME = "search";
	
	// Explicit Wait Timeout
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_LONG_TIME_OUT = 20;
	
	// Application Constants
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";

	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String ACC_PAGE_FRACTION_URL = "route=account/account";
	
	public static final String LOGIN_ERROR_MESSAGE = "Warning: No match for E-Mail Address and/or Password.";
	public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;
	public static final List<String> EXPECTED_ACC_PAGE_HEADERS_LIST = List.of("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	


}
