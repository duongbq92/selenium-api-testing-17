 package testApp;

public class Topic_10_Path {
	static String source_folder = System.getProperty("user.dir");
	static String absolute = "E:\\Automation Testing\\02 - Selenium API\\uploadFile\\Hana1.jpg";
	static String relative = source_folder + "\\uploadFile\\Hana1.jpg";
	String imageName = "xx";// neu khong phai la bien staic thi ko dc goi truc tiep, ma phai thong qua doi tuong
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Topic_10_Path topic10 = new Topic_10_Path();
		System.out.println(source_folder);
		System.out.println(absolute);
		System.out.println(relative);
		System.out.println(topic10.imageName);
	}

}
