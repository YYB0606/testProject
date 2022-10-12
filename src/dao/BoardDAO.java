package dao;

import java.util.List;
import java.util.Map;
import util.JDBCUtil;

public class BoardDAO {
	private static BoardDAO instance = null;
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		if(instance == null) instance = new BoardDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public List<Map<String, Object>> getList() {
		String sql = "SELECT * FROM JAVA_BOARD ORDER BY NUM DESC";
		return jdbc.selectList(sql);
	}
	
	public int insert(List<Object> param) {
		String sql = "INSERT INTO JAVA_BOARD (NUM,TITLE,WRITER,CONTENT,DATETIME) "
				+ " VALUES (JAVA_BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		return jdbc.update(sql,param);
	}
	
	public Map<String, Object> getInfo(List<Object> param) {
		String sql = "SELECT * FROM JAVA_BOARD WHERE NUM=? ";
		return jdbc.selectOne(sql,param);
	}
	
	public Map<String, Object> update(List<Object> param) {
		String sql = "UPDATE (TITLE,WRITER,CONTENT)"
				+ "SET (?,?,?) ";
		return jdbc.update(sql,param);
	}
	
	public int delete(List<Object> param) {
		String sql = "DELETE FROM JAVA_BOARD WHERE NUM=?";
		return jdbc.update(sql,param);
	}
}
