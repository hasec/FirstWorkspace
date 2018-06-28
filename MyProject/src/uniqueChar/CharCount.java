package uniqueChar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CharCount {
	
	private int count = 0;
	private int countMultiply = 3;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCountMultiply() {
		return countMultiply;
	}
	public void setCountMultiply(int countMultiply) {
		this.countMultiply = countMultiply;
	}
	
	public void plusCount() {
		this.count = this.count+1;
	}
	
	public void multiplyCount() {
		this.countMultiply = this.countMultiply * 3;
	}
	
	public void calculate() {
		this.plusCount();
		this.multiplyCount();
	}
}
