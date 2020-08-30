package testApp;

public class Topic_09_Split {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String linkText =  "http://the-internet.herokuapp.com/";
		String splitLink[] = linkText.split("//");
		System.out.println(splitLink[0]);
		System.out.println(splitLink[1]);
		linkText = splitLink[0] + "//"+"pass"+splitLink[1];
		System.out.println(linkText);
	}
	
}
