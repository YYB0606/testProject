package util;

public interface View {
	int HOME = 1;
	
	// 회원관련
	int MEMBER = 2;
	int MEMBER_LOGIN = 21;
	int MEMBER_SIGNUP = 22;
	
	// 게시판 관련
	int BOARD = 3;
	int BOARD_LIST = 31;
	int BOARD_DETAIL = 32;
	int BOARD_INSERT = 33;
	int BOARD_UPDATE = 34;
	int BOARD_DELETE = 35;
	
}
