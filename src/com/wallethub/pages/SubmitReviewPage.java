package com.wallethub.pages;

import com.wallethub.commonactions.CommonActions;

public class SubmitReviewPage {
	public static final String DROPDOWN="dropdown";
	public static final String REVIEWBOX="reviewBox";
	public static final String SUBMIT="submit";
	public static final String HEALTH="health";
	public static final String STAR="star";
	public static final String CONFIRMATION="confirmation";

	
	public SubmitReviewPage()
	{
	String className = this.getClass().getSimpleName();
	CommonActions.readObjectFile(className);
	}
}
