import java.util.Scanner;

public class adventOfCode6{

	public static void main(String[] args) {
		int lengthOfCheck = 14;
		String data = getInput(); 
		//System.out.println("length of data: " + data.length());
		for (int i = 0; i < data.length() - lengthOfCheck; i++) {
			String focus = data.substring(i, i + lengthOfCheck + 1);
			if (isDistinct(focus)) {
				System.out.println("Distinct at " + (i + lengthOfCheck));
				break;
			}
		}


	}
	public static String getInput() {
		Scanner input = new Scanner(System.in);
		String data = input.nextLine();
		return data;
	}
	public static boolean isDistinct(String data) {
		char[] dataAsArr = new char[data.length()];
		for (int i = 0; i < data.length(); i++) {
			dataAsArr[i] = data.charAt(i);
		}
		for (int i = 0; i < data.length(); i++) {
			for (int j = 0; j < data.length(); j++) {
				if (data.charAt(i) == dataAsArr[j] && i != j) {
					return false;
				}
			}
		}
		return true;
	}
}
