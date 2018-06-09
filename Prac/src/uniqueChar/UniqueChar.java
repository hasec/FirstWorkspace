package uniqueChar;

import java.util.Scanner;

public class UniqueChar {
	public static void main(String[] args) {
		
		String check = "Hello";
		
		char[] checkThis = check.toCharArray();
		
		for(int i=0;i<checkThis.length-1;i++) {
			for(int j=i-1;j>=0;j--) {
				if(checkThis[j]==checkThis[j+1]) {
					System.out.println(j+"번째 문자와 "+(j+1)+"번째 문자가 같습니다 ");
				}
			}
		}
	}
}
