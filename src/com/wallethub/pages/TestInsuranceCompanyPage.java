package com.wallethub.pages;

import com.wallethub.commonactions.CommonActions;

public class TestInsuranceCompanyPage {
	public static final String REVIEW_AREA="reviewarea";
	public static final String STAR1="star1";
	public static final String STAR2="star2";
	public static final String STAR3="star3";
	public static final String STAR4="star4";

	
	public TestInsuranceCompanyPage()
	{
	String className = this.getClass().getSimpleName();
	CommonActions.readObjectFile(className);
	}
	
	public void hoverOverStars()
	{

		CommonActions.hover(REVIEW_AREA);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CommonActions.hover(STAR1);
		
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CommonActions.hover(STAR2);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		CommonActions.hover(STAR3);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommonActions.hover(STAR4);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommonActions.click(STAR4);
		

	}
	
}
