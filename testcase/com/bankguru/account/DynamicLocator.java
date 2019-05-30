package com.bankguru.account;

public class DynamicLocator {
	
	public static void main(String[] args) {
		
		 String DYNAMIC_LINK = "//ul[@class='menusubnav']//a[text()='%s']";
		 String DYNAMIC_BUTTON_LINK = "//ul[@class='menusubnav']//a[text()='%s']//button[text()='%s']";
		 String DYNAMIC_EDIT_ROW = "//td[@data-key='females' and text()='384187']/following-sibling::td[@data-key='country' and text()='Afghanistan']/following-sibling::td[@data-key='males' and text()='407124']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-edit-row-btn']";
		 String DYNAMIC_DELETE_ROW = "//td[@data-key='females' and text()='384187']/following-sibling::td[@data-key='country' and text()='Afghanistan']/following-sibling::td[@data-key='males' and text()='407124']/following-sibling::td[@data-key='total' and text()='791312']/preceding-sibling::td[@class='qgrd-actions']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-edit-row-btn']";
	
		 clickToLink(DYNAMIC_LINK, "Manager");
		 clickToLink(DYNAMIC_LINK, "New Customer");
		 clickToLink(DYNAMIC_LINK, "Edit Customer");
		 
		 clickToLink(DYNAMIC_EDIT_ROW, "384187", "Afghanistan", "407124");
		 clickToLink(DYNAMIC_EDIT_ROW, "175157", "Australia/New Zeland", "186032");
		 clickToLink(DYNAMIC_DELETE_ROW, "175157", "Australia/New Zeland", "186032", "791312");
		 
	}
	
	public static void clickToLink(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		System.out.println("CLick to element" + locator);
	}

}
