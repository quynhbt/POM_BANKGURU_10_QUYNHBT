package bankguru;

public class DepositPageUI {
	public static final String AMOUNT_DEPOSIT_FORM_MESSAGE = "//p[text()='Amount Deposit Form']";
	public static final String ACCOUNT_NO_TEXTBOX = "//input[@name='accountno']";
	public static final String AMOUNT_TEXTBOX = "//input[@name='ammount']";
	public static final String DESCRIPTION_TEXTBOX = "//input[@name='desc']";
	public static final String DEPOSIT_SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	public static final String DEPOSIT_RESET_BUTTON = "//input[@name='res']";
	public static final String SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	public static final String RESET_BUTTON = "//input[@name='res']";

	public static final String TRANSACTION_DETAILS_MESSAGE = "//p[text()='Transaction details of Deposit for Account %s']";
	public static final String CURRENT_BALANCE_INFOR = "//td[text()='Current Balance']//following-sibling::td";
	public static final String WITHDRAW_LINK = "//a[text()='Withdrawal']";
}