package jdbcMemo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class MemoMain {
	public static void main(String[] args) {
	 Scanner scanner = new Scanner(System.in);
	 List<Memo> list = new ArrayList<>();
	 PreparedStatement pstmt=null;
	 Connection conn=null;
	 String crSql = "create table Memo(title varchar(30),text varchar(30),tag varchar(30))";
	 String inSql = "insert into Memo values(?,?,?)";
	 try {
		  conn = ConnDB.getInstance().getConnection();
		  pstmt = conn.prepareStatement(crSql);
		  pstmt.executeUpdate();
		  System.out.println("메모작성");
		  while(true) {
			  System.out.println("제목>");
			  String title = scanner.next();
			  System.out.println("본문>");
			  String text = scanner.next();
			  System.out.println("태그>");
			  String tag = scanner.next();
			  list.add(new Memo(title, text, tag));
			  System.out.println("중지하시겠습니까?(y/n)>");
			  if(scanner.next().equalsIgnoreCase("y")) break;
		  }
		  pstmt = conn.prepareStatement(inSql);
		  for(int i=0;i<list.size();i++) {
			  pstmt.setString(1, list.get(i).getTitle());
			  pstmt.setString(2, list.get(i).getText());
			  pstmt.setString(3, list.get(i).getTag());
			  pstmt.executeUpdate();
		  }
		  pstmt = conn.prepareStatement("select * from Memo");
		  ResultSet rs = pstmt.executeQuery();
		  while(rs.next()) {
			  System.out.println(rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3));
		  }
	 }catch(Exception e) {
		 System.out.println(e.getMessage());
	 }
	}
}
