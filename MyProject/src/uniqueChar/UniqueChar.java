package uniqueChar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class UniqueChar {

	public static void main(String[] args) {
		String check = "abcdaabebe";
		HashMap<Character, CharCount> checkMap = new HashMap<Character, CharCount>();
		
		int max = maxCountKeys(check, checkMap);

		List<Character> maxChar = getMaxCharactoers(checkMap, max);

		for (int i = 0; i < maxChar.size(); i++) {
			System.out.print(maxChar.get(i) + " ");
		}
		System.out.print(max);

	}

	private static List<Character> getMaxCharactoers(HashMap<Character, CharCount> checkMap, int max) {
		List<Character> maxChar = new ArrayList<Character>();
		for(Entry<Character,CharCount> var:checkMap.entrySet()) {
			if(var.getValue().getCount()==max) {
				maxChar.add(var.getKey());
			}
		}
		return maxChar;
	}

	private static int maxCountKeys(String check, HashMap<Character, CharCount> checkMap) {
		int max =0;
		
		for (int i = 0; i < check.length(); i++) {
			char key = check.charAt(i);
			CharCount count = checkMap.getOrDefault(key, new CharCount());
			count.plusCount();
			count.multiplyCount();
			
			if (count.getCount() > max) {
				max = count.getCount();
			}
			checkMap.put(key, count);
		}
		return max;
	}
}
