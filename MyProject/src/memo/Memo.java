package memo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Memo {
	
	List<List> memoList = new ArrayList<>();
	String inputTag;
	StringTokenizer tokens;
	Scanner scanner = new Scanner(System.in);
	Pattern pattern;
	Matcher matcher;
	
	void run() {
		boolean run=true;
			while(run) {
				System.out.print("1. 글입력\n2. 글조회\n3. 글수정\n4. 종료\n번호입력>");
				int pick = scanner.nextInt();
				switch (pick) {
				case 1:
					saveMemo();
					break;
				case 2:
					readMemo();
					break;
				case 3:
					remakeMemo();
					break;
				case 4:
					run=false;
			}
		}
	}
	
	void remakeMemo() {
		boolean run = true;
		System.out.println("수정할 글을 선택하세요");
		for(int i=0;i<memoList.size();i++) {
			info(i);
		}
		int remakeMemo = scanner.nextInt();
		while(run) {
			System.out.println("무엇을 수정하시겠습니까?");
			System.out.println("1.제목 2.본문 3.태그 4.완료");
			int remakeMenu = scanner.nextInt();
			switch(remakeMenu) {
				case 1:
					System.out.println("수정할 제목을 입력하세요");
					String reTitle = scanner.next();
					StringBuilder sbTitle = new StringBuilder(reTitle);
					memoList.get(remakeMemo-1).set(0, sbTitle);
					break;
				case 2:
					System.out.println("수정할 본문을 입력하세요");
					String reText = scanner.next();
					StringBuilder sbText = new StringBuilder(reText);
					memoList.
					get(remakeMemo-1).set(1, sbText);
					break;
				case 3:
					System.out.println("수정할 태그를 입력하세요");
					String reTag = scanner.next();
					StringBuilder sbTag = new StringBuilder(reTag);
					memoList.get(remakeMemo-1).set(2, sbTag);
					break;
				case 4:
					run=false;
					break;
			}
		}
	}

	void info(int memoNumber) {
		System.out.println((memoNumber+1)+"번 메모");
		System.out.println("제목: "+memoList.get(memoNumber).get(0));
		System.out.println("본문: "+memoList.get(memoNumber).get(1));
		System.out.println("태그: "+memoList.get(memoNumber).get(2));
	}
	
	void readMemo() {
		System.out.println("검색할 태그를 입력하세요");
		String search = scanner.next();
		int tagLength=0;
		boolean run=true;
		
		for(int i=0;i<memoList.size();i++) {
			List<String> tagList = new ArrayList<>();
			String[] tagArray = memoList.get(i).get(2).toString().split(",");
			for(int j=0;j<tagArray.length;j++) {
				tagList.add(tagArray[j]);
			}
			for(int j=0;j<tagList.size();j++) {
				if(tagList.get(j).equals(search)) {
					info(i);
				}
			}
		}
		System.out.println();
	}

	void saveMemo() {
		List<StringBuilder> memoMenu = new ArrayList<>();
		String title;
		String text;
		String tag;
		System.out.println("제목을 입력하세요");
		title = scanner.next();
		StringBuilder sbTitle = new StringBuilder(title);
		
		System.out.println("본문을 입력하세요");
		text = scanner.next();
		StringBuilder sbText = new StringBuilder(text);
		
		System.out.println("태그를 입력하세요 ,로 여러개 입력가능");
		tag = scanner.next();
		StringBuilder sbTag = new StringBuilder(tag); 
		
		memoMenu.add(sbTitle);
		memoMenu.add(sbText);
		memoMenu.add(sbTag);
		memoList.add(memoMenu);
		System.out.println();
	}
}
