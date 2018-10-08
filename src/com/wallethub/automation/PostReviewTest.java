package com.wallethub.automation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wallethub.commonactions.BaseTestCase;
import com.wallethub.commonactions.CommonActions;
import com.wallethub.pages.SubmitReviewPage;
import com.wallethub.pages.TestInsuranceCompanyPage;
import com.wallethub.pages.UserProfilePage;
import com.wallethub.pages.WallethubLoginPage;
@Test()
public class PostReviewTest extends BaseTestCase{
	
public static final String TEST_DATA_FILE="ReviewPostingTestData.csv";

	public void postReview() 
	{
		
		CommonActions.readTestData(TEST_DATA_FILE);
		
		CommonActions.login();
		
		CommonActions.waitMilliSeconds(2000);
		
		CommonActions.navigate(WallethubLoginPage.URL);
		
		TestInsuranceCompanyPage testInsurance = new TestInsuranceCompanyPage();
		
		testInsurance.hoverOverStars();
		
		
		new SubmitReviewPage();
		CommonActions.click(SubmitReviewPage.DROPDOWN);
		CommonActions.click(SubmitReviewPage.HEALTH);
		CommonActions.waitMilliSeconds(2000);
		WebElement Element = CommonActions.returnElement(SubmitReviewPage.STAR);
		wait.until(ExpectedConditions.elementToBeClickable(Element));
		CommonActions.javaScriptClick(SubmitReviewPage.STAR);
		CommonActions.enterText(SubmitReviewPage.REVIEWBOX);
		CommonActions.click(SubmitReviewPage.SUBMIT);
		
		Assert.assertTrue(CommonActions.verifyPresence(SubmitReviewPage.CONFIRMATION));
		
		CommonActions.navigate(WallethubLoginPage.HOME_URL);
		
		new UserProfilePage();
		CommonActions.verifyPresence(UserProfilePage.REVIEW_LINK);
		CommonActions.click(UserProfilePage.REVIEW_SECTION);
		String actualReviewText = CommonActions.getText(UserProfilePage.REVIEW_TEXT);
		Assert.assertTrue(CommonActions.compareStrings(actualReviewText, testDataMap.get(SubmitReviewPage.REVIEWBOX)));
	}
		
}