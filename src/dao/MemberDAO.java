package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MemberDAO {
	private static MemberDAO instance = null;
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(instance == null) instance = new MemberDAO();
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public int signup(List<Object> param) {
		String sql = "INSERT INTO JAVA_MEMBER(MEM_ID, MEM_PASS, MEM_NAME) " 
					+ "VALUES (?, ?, ?)";
		return jdbc.getInstance().update(sql,param);
	}
	
	public Map<String, Object> login(List<Object> param) {
		String sql = "SELECT * FROM JAVA_MEMBER WHERE MEM_ID=? AND MEM_PASS=?";
		return jdbc.selectOne(sql, param);
	}
}
