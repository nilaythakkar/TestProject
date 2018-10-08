package com.wallethub.pages;

import com.wallethub.commonactions.CommonActions;

public class UserProfilePage {
	public static final String REVIEW_LINK="ReviewLink";
	public static final String REVIEW_TEXT="ReviewText";
	public static final String REVIEW_SECTION="ReviewSection";

	
	public UserProfilePage()
	{
	String className = this.getClass().getSimpleName();
	CommonActions.readObjectFile(className);
	}
}
