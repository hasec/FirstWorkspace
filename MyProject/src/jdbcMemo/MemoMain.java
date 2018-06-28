package jdbcMemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class MemoMain {
	static Scanner scanner = new Scanner(System.in);
	static List<Memo> list = new ArrayList<>();
	static PreparedStatement pstmt=null;
	static Connection conn=ConnDB.getInstance().getConnection();
	
	void run() throws SQLException {
		//String ctSql1 = "create table memo(memoNum int AUTO_INCREMENT,"
		//			  + "title varchar(30),"
		//			  + "text varchar(30),"
		//			  + "tag varchar(30),"
		//			  + "PRIMARY KEY(memoNum))";
		//String ctSql2 = "create table tag(tag varchar(30))";
		//pstmt = conn.prepareStatement(ctSql1);
		//pstmt.executeUpdate();
		//pstmt = conn.prepareStatement(ctSql2);
		//pstmt.executeUpdate();
		boolean run = true;
		while(run) {
			System.out.println("1.메모작성\n2.메모수정\n3.메모검색\n4.종료");
			int pick = scanner.nextInt();
			switch(pick) {
			case 1:
				makeMemo();
				break;
			case 2:
				remakeMemo();
				break;
			case 3:
				searchMemo();
				break;
			case 4:
				run=false;
			}
		}
	}
	
	void makeMemo() throws SQLException{
		String inSql = "insert into memo(title,text,tag) values(?,?,?)";
		System.out.println("메모작성");
		boolean run = true;
		  while(run) {
			  System.out.println("제목>");
			  String title = scanner.next();
			  System.out.println("본문>");
			  String text = scanner.next();
			  System.out.println("태그>");
			  String tag = scanner.next();
			  list.add(new Memo(title, text, tag));
			  System.out.println("중지하시겠습니까?(y/n)>");
			  if(scanner.next().equalsIgnoreCase("y")) {
				  run=false;
			  }
		  }
		  pstmt = conn.prepareStatement(inSql);
		  for(int i=0;i<list.size();i++) {
			  pstmt.setString(1, list.get(i).getTitle());
			  pstmt.setString(2, list.get(i).getText());
			  pstmt.setString(3, list.get(i).getTag());
			  pstmt.executeUpdate();
		  }		
	}
	
	void remakeMemo() throws SQLException {
		pstmt = conn.prepareStatement("select * from memo");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1)+"번째 메모\n"
							  +" 제목: "+rs.getString(2)
							  +" 본문: "+rs.getString(3)
							  +" 태그: "+rs.getString(4));
		}
		System.out.println("수정할 메모를 선택하세요");
		int pick = scanner.nextInt();
		
		boolean run=true;
		while(run) {
			System.out.println("1.제목수정 2.본문수정 3.태그수정 4.수정완료");
			int remakePick = scanner.nextInt();
			switch(remakePick) {
			case 1:
				String titleSql = "update memo set title=? where memoNum="+"'"+pick+"'";
				System.out.println("수정할 제목을 입력하세요");
				String title = scanner.next();
				pstmt = conn.prepareStatement(titleSql);
				pstmt.setString(1, title);
				pstmt.executeUpdate();
				break;
			case 2:
				String textSql = "update memo set text=? where memoNum="+"'"+pick+"'";
				System.out.println("수정할 본문을 입력하세요");
				String text = scanner.next();
				pstmt = conn.prepareStatement(textSql);
				pstmt.setString(1, text);
				pstmt.executeUpdate();
				break;
			case 3:
				String tagSql = "update memo set tag=? where memoNum="+"'"+pick+"'";
				System.out.println("수정할 태그를 입력하세요");
				String tag = scanner.next();
				pstmt = conn.prepareStatement(tagSql);
				pstmt.setString(1, tag);
				pstmt.executeUpdate();
				break;
			case 4:
				run=false;
				break;
			}
		}
	}
	
	void searchMemo() throws SQLException {
		boolean run=true;
		while(run) {
			System.out.println("검색할 태그를 입력하세요");
			String tag = scanner.next();
			String sql = "select * from memo where tag like "+"'%"+tag+"%'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1)+"번째 메모\n"
								  +" 제목: "+rs.getString(2)
								  +" 본문: "+rs.getString(3)
								  +" 태그: "+rs.getString(4));
			}
			System.out.println("검색을 중단합니까?(y/n)");
			if(scanner.next().equalsIgnoreCase("y"))
				run=false;
		}
	}
	
	
	public static void main(String[] args) throws SQLException {
		MemoMain memoMain = new MemoMain();
		memoMain.run();
		System.out.println("프로그램 종료");
	}
}
