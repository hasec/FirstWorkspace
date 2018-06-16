package uniqueChar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UniqueChar {

	public static void main(String[] args) {
		String check = "abcdaabebe";
		HashMap<Character, int[][]> checkMap = new HashMap<Character, int[][]>();
		List<Character> maxChar = new ArrayList<Character>();
		int max = 0;
		
		for(int i=0;i<check.length();i++) {
			char key = check.charAt(i);
			int[][] val = new int[1][2];
			if(checkMap.containsKey(key)) {
				int[][] tempval =checkMap.get(key);
				val[0][0] = tempval[0][0]+1;
				val[0][1] = tempval[0][1]*3;
			}else {
				val[0][0] = 1;
				val[0][1] = 3;
			}
			if(val[0][0]>max) {
				max = val[0][0];
			}
			checkMap.put(key, val);
			//System.out.println(key+" "+val[0][0]+" "+val[0][1]);
		}
		
		for(int i=0;i<check.length();i++) {
			char key = check.charAt(i);
			int[][] temp = checkMap.get(key);
			if(temp[0][0]==max) {
				maxChar.add(key);
			}
		}
		
		Set temp = new LinkedHashSet<Character>();
		temp.addAll(maxChar);
		maxChar.clear();
		maxChar.addAll(temp);
		
		for(int i=0;i<maxChar.size();i++) {
			System.out.print(maxChar.get(i)+" ");
		}
		System.out.print("가 "+max+"번 중복된 문자열입니다");
		
	}
}