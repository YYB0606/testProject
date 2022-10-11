package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.BoardDAO;
import util.ScanUtil;
import util.SpaceUtil;
import util.View;

public class BoardService {
	private static BoardService instance = null;
	private BoardService() {} // private 생성자 -> 싱글톤
	public static BoardService getInstance() {
		if(instance == null) instance = new BoardService();
		return instance;
	}

	BoardDAO boardDAO = BoardDAO.getInstance();
	
	public int list() {
		if(!(boolean)Controller.sessionStorage.get("login")) { // == !true
			System.out.println("로그인이 되어있지 않습니다.");
			return View.MEMBER_LOGIN;
		}
		System.out.println("-- 게시판 --");
		System.out.printf("%s|%s|%s|%s"
				,SpaceUtil.format("번호", 6, 0)
				,SpaceUtil.format("제목", 25, 0)
				,SpaceUtil.format("작성자", 12, 0)
				,SpaceUtil.format("작성일", 12, 0)
			);
		System.out.println();
		
		List<Map<String, Object>> boardList = boardDAO.getList();
		for(Map<String, Object> row : boardList) {
			System.out.printf("%s|%s|%s|%s"
					,SpaceUtil.format(row.get("NUM"), 6, 0)
					,SpaceUtil.format(row.get("TITLE"), 25, 0)
					,SpaceUtil.format(row.get("WRITER"), 12, 0)
					,SpaceUtil.format(row.get("DATETIME"), 12, 0)
				);
			System.out.println();
		}
		System.out.println("------------------------------");
		System.out.println("1.상세 2.등록 3.수정 4.삭제 0.홈으로");
		switch(ScanUtil.nextInt()) {
		case 1: return View.BOARD_DETAIL;
		case 2: return View.BOARD_INSERT;
		case 3: return View.BOARD_UPDATE;
		case 4: return View.BOARD_DELETE;
		case 0: default: return View.HOME;
		}
	}
	
	public int insert() {
		System.out.println("-- 게시물 등록 --");
		System.out.print("제목 >> ");
		String title = ScanUtil.nextLine();
		
//		String writer = (String)(
//				((Map<String, Object>)(Controller.sessionStorage.get("userInfo"))).get("MEM_NAME"));
		Map<String, Object> userInfo 
			= (Map<String,Object>)Controller.sessionStorage.get("userInfo");
			   // sessionStrorage에 나온 정보가 원하는 데이터타입과 안 맞을 수 있다는 경고
		String writer = (String)userInfo.get("MEM_NAME");
		
		System.out.println("내용 >> ");
		String content = ScanUtil.nextLine();
		
		List<Object> param = new ArrayList<>();
		param.add(title);
		param.add(writer);
		param.add(content);
		
		int result = boardDAO.insert(param);
		if(result > 0) {
			System.out.println("등록 성공!");
		}else{
			System.out.println("등록 실패!");
		}
		return View.BOARD_LIST;
	}
	
	public int detail() {
		System.out.println("게시글 번호 입력 >> ");
		int num = ScanUtil.nextInt();
		List<Object> param = new ArrayList<>();
		param.add(num);
		
		Map<String, Object> boardInfo = (Map<String, Object>) boardDAO.getInfo(param);
		if(boardInfo == null) {
			System.out.println("없는 번호 입니다.");
			return View.BOARD_LIST;
		}
		System.out.println("-- 게시물 정보 --");
		System.out.println("제목 : " + boardInfo.get("TITLE"));
		System.out.println("작성자 : " + boardInfo.get("WRITER"));
		System.out.println("작성일자 : " + boardInfo.get("DATETIME"));
		System.out.println("내용 : " + boardInfo.get("CONTENT"));
		System.out.println("------------------------------");
		return View.BOARD_LIST;
	}
	
	public int update() {

		return View.BOARD_LIST;
	}
}

