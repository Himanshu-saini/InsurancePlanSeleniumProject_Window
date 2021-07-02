
package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.TestBase;
import pages.HomePage;
import pages.TravelInsurancePage;
import util.TravelInsuranceExcel;


public class TravelInsuranceDetailsTest extends TestBase {

	HomePage homePage;
	TravelInsurancePage travelInsurance = new TravelInsurancePage();
	By destination = By.id("destination-autocomplete");

	public TravelInsuranceDetailsTest() throws Exception {
		super();
	}

	@BeforeSuite(groups= {"Default","Regression"})
	public void setup() throws Exception {
		initialization();
		
		homePage = new HomePage();
	}

	@DataProvider(name = "dp_travelData")
	public Object[] dp() throws IOException {

		Object[] obj;

		obj = TravelInsuranceExcel
				.readData(System.getProperty("user.dir") + "\\src\\test\\java\\DataTables\\TravelData.xlsx"); 
		System.out.println(obj);
		return obj;
	}

	@Test(priority = 1,groups= {"Default","Smoke","Regression"})
	public void homePageTitle() throws Exception
	{
		String title = homePage.clickTravelInsurance();
		Assert.assertEquals(title, "Travel Insurance: Buy Travel Insurance Online with Covid-19 Coverage");
	}

	@Test(dataProvider = "dp_travelData", priority = 2,groups= {"Default","Regression"})
	public void getStudentTravelInsurance(String country, String age1, String age2, String startDate, String endDate, String mobileNumber) throws Exception {
		fillStudentDetails(country,age1,age2,startDate,endDate,mobileNumber);
		setStudentPlanOption();
		sortPrice();
		getInsuranceDetails();
	}
	
	public void fillStudentDetails(String country, String age1, String age2, String startDate, String endDate, String mobileNumber) throws InterruptedException, IOException {
		travelInsurance.fillStudentDetails(country,age1,age2,startDate,endDate,mobileNumber);
		/*logger.log(Status.PASS, "Filling Details");
		logger.log(Status.INFO, "Filling Details");*/
	}
	
	public void getFreeQuotes() throws InterruptedException, IOException
	{
		//travelInsurance.getFreeQuotes();
		logger.log(Status.PASS, "getFreeQuotes");
	
	}

	public void setStudentPlanOption() throws IOException{
		travelInsurance.setStudentPlanOption();
		/*logger.log(Status.PASS, "StudentPlan");
		logger.log(Status.INFO, "Student plan option selected");*/
	}
	public void sortPrice() throws IOException{
		travelInsurance.sortPrice();
		/*logger.log(Status.PASS, "sortPrice");
		logger.log(Status.INFO, "Clicking on sort price dropdown");*/
	}
	
	public void getInsuranceDetails(){
		travelInsurance.insuranceDetails();
		/*logger.log(Status.PASS, "getinsuranceProviderName");
		logger.log(Status.INFO, "Clicking on sort price dropdown");*/
		
	}

}
