package bankguru;

public class LoginPageUI {

    public static final String LOGIN_FORM = "//form[@name='frmLogin']";
	public static final String USER_ID_TEXTBOX = "//input[@name='uid']";
	// public: pham vi truy cap trong tat ca framework (class nao cung co the accessvao bien nay duoc)
	// static: bien tinh - cac class khac co the truy cap vao bien nay ma ko can
	// khoi tao class LoginPageUI lene
	// final: gia tri nay khong the gan lai
	// static + final: la hang so
	// ten doi tuong: viet hoa va phan tach bang dau gach noi: ten doi tuong + kieu element cua doi tuong do(vi du textbox,button, radiobuttpn, checkbox,table...)
	public static final String PASSWORD_TEXTBOX = "//input[@name='password']";
	public static final String Login_BUTTON = "//input[@name='btnLogin']";
	public static final String HERE_LINK = "//a[text()='here']";

}