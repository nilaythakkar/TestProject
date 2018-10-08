package com.wallethub.pages;

import com.wallethub.commonactions.CommonActions;

public class WallethubLoginPage {

	public static final String EMAIL="email";
	public static final String PASSWORD="password";
	public static final String LOGIN="login";
	public static final String URL="url";
	public static final String HOME_URL = "homeurl"; 

	public WallethubLoginPage()
	{
	String className = this.getClass().getSimpleName();
	CommonActions.readObjectFile(className);
	}

}
