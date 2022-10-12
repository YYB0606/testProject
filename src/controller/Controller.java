package controller;

import java.util.HashMap;
import java.util.Map;

import service.BoardService;
import service.MemberService;
import util.ScanUtil;
import util.View;

public class Controller {
	// 세션 : 사용자와 서버의 연결 파이프라인 이라고 생각하면 쉬움
	static public Map<String, Object> sessionStorage = new HashMap<>();
	
	MemberService memberService = MemberService.getInstance();
	BoardService boardService = BoardService.getInstance();
	
	public static void main(String[] args) {
		new Controller().start();
	}
	
	private void start() { // APDLS
		sessionStorage.put("login", false);
		sessionStorage.put("loginInfo", null);
		int view = View.HOME; // static final이어서 view라는 객체를 만들지 않아도 꺼낼 수 있음
		while(true) {
			switch(view) {
			case View.HOME: view = home(); break;
			case View.MEMBER_SIGNUP: view = memberService.signup(); break;
			case View.MEMBER_LOGIN: view = memberService.login(); break;
			case View.BOARD_LIST: view = boardService.list(); break;
			case View.BOARD_INSERT: view = boardService.insert(); break;
			case View.BOARD_DETAIL: view = boardService.detail(); break;
			case View.BOARD_UPDATE: view = boardService.update(); break;
			case View.BOARD_DELETE: view = boardService.delete(); break;
			default: view = home(); break;
			}
		}
	}
	
	private int home() {
		System.out.println("====== 대덕인재 개발원 ======");
		System.out.println("1. 로그인 2.회원가입 3.게시판");
		System.out.println("=======================");
		System.out.print("번호 입력 >> ");
		switch(ScanUtil.nextInt()) {		
		case 1: return View.MEMBER_LOGIN;
		case 2: return View.MEMBER_SIGNUP;
		case 3: return View.BOARD_LIST;
		default: return View.HOME;
		}
	}
}
