package jdbcMemo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnDB {
	//필드(해당클래스 타입의 instance)
	static ConnDB instance=new ConnDB();
	//생성자 private - 외부에서 생성을 못하게 막음
	private ConnDB() {}
	//getInstance()로 호출
	public static ConnDB getInstance() {
		return instance;
	}
	//연결맺기
	public static Connection getConnection() {
		Connection conn=null;
		String url="jdbc:mysql://localhost:3306/javadb";
		String user="scott";
		String password="tiger";
		try {
			//1.드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			//2.연결맺기
			conn = DriverManager.getConnection(url, user, password);

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
