package memo02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
 
public class MemoMain {
	Scanner scanner = new Scanner(System.in);
	List<Memo> memoList = new ArrayList<>();
	Map<String, List<Memo>> searchMemo = new HashMap<>();
	
	void run() {  
		boolean run=true;
			while(run) {
				System.out.print("1. 글 생성하기\n2. 글 조회하기\n3. 글 수정하기\n4. 종료\n입력>");
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
	
	void saveMemo() {
		System.out.println("제목을 입력하세요");
		String title = scanner.next();
		System.out.println("내용을 입력하세요");
		String text = scanner.next();
		System.out.println("태그를 입력하세요(,로 다중입력가능)");
		String tag = scanner.next();
		Memo memo = new Memo(title, text, tag);		
		memoList.add(memo);
		
		String[] tags = tag.split(",");
		ArrayList<Memo> tagMemos = new ArrayList<>();
		tagMemos.add(memo);

		for(int i=0;i<tags.length;i++) {
			if(searchMemo.containsKey(tags[i])) {
				searchMemo.get(tags[i]).add(memo);
			}else {
				searchMemo.put(tags[i], tagMemos);
			}
		}
	}
	
	private void readMemo() {
		System.out.println("검색할 태그를 입력하세요");
		String inputTag = scanner.next();
		for(int i=0;i<searchMemo.get(inputTag).size();i++) {
			System.out.println(searchMemo.get(inputTag).get(i));
		}
	}
	
	private void remakeMemo() {
		System.out.println("수정할 글을 선택하세요");
		for(int i=0;i<memoList.size();i++) {
			System.out.println((i+1)+"번째 글"+memoList.get(i).toString());
		}
		int memoPick = scanner.nextInt()-1;
		boolean run = true;
		while(run) {
			System.out.println("무엇을 수정합니까?\n1.제목 2.본문 3.태그 4.수정완료");
			int remakePick = scanner.nextInt();
			switch(remakePick) {
				case 1:
					System.out.println("새로운 제목을 입력하세요");
					String newTitle = scanner.next();
					memoList.get(memoPick).setTitle(newTitle);
				case 2:
					System.out.println("새로운 본문을 입력하세요");
					String newText = scanner.next();
					memoList.get(memoPick).setText(newText);
				case 3:
					System.out.println("새로운 태그를 입력하세요 (,)로 다중입력 가능");
					String newTag = scanner.next();
					memoList.get(memoPick).setTag(newTag);
					
					String[] tags = memoList.get(memoPick).getTag().split(",");
					String[] newTags = newTag.split(",");
					
					List<String> removeTagsList = new ArrayList<>(Arrays.asList(tags));
					List<String> minRemoveTagsList = new ArrayList<>(Arrays.asList(newTags));
					removeTagsList.removeAll(minRemoveTagsList);
					
					for(int i=0;i<removeTagsList.size();i++) {
						for(int j=0;j<searchMemo.get(removeTagsList.get(i)).size();j++) {
							if(searchMemo.get(removeTagsList).get(j).tag==newTag) {
								searchMemo.get(removeTagsList).remove(j);
							}
						}
					}
					
					List<String> addTagList = new ArrayList<>(Arrays.asList(newTags));
					List<String> minAddTagList = new ArrayList<>(Arrays.asList(tags));
					addTagList.removeAll(minAddTagList);
					
					List<Memo> addMemo = new ArrayList<>();
					addMemo.add(memoList.get(memoPick));
					for(int i=0;i<addTagList.size();i++) {
							searchMemo.put(addTagList.get(i), addMemo);
					}
				case 4:
					run=false;
			}
		}
	}
	
	public static void main(String[] args) {
		MemoMain memo = new MemoMain();
		memo.run();
		System.out.println("프로그램 종료");
	}
}
