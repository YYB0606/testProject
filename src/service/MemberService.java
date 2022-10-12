package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.MemberDAO;
import util.ScanUtil;
import util.View;

public class MemberService {
	private static MemberService instance = null;
	private MemberService() {}
	public static MemberService getInstance() {
		if(instance == null) instance = new MemberService();
		return instance;
	}
	
	MemberDAO memberDAO = MemberDAO.getInstance(); // 기능별로 모듈화를 시켜둠
	
	private List<Object> getIdPass(){
		System.out.print("아이디 >> ");
		String memId = ScanUtil.nextLine();
		System.out.print("비밀번호 >> ");
		String memPass = ScanUtil.nextLine();
		
		List<Object> param = new ArrayList<>(); 
		param.add(memId);
		param.add(memPass);
		return param;
	}
	
	public int signup() {
		System.out.println("-- 회원가입 --");
//		System.out.print("아이디 >> ");
//		String memId = ScanUtil.nextLine();
//		System.out.print("비밀번호 >> ");
//		String memPass = ScanUtil.nextLine();
		System.out.print("이름 >> ");
		String memName = ScanUtil.nextLine();
		
		List<Object> param = getIdPass(); 
//		param.add(memId);
//		param.add(memPass);
		param.add(memName);
		
		int result = memberDAO.signup(param);
		if(result > 0) {
			System.out.println("등록 성공!");
		}else {
			System.out.println("등록 실패!");
		}
		return View.HOME;
	}
	
	public int login() {
		if((boolean)Controller.sessionStorage.get("login")) { // == true
			System.out.println("이미 로그인 되어 있습니다.");
			return View.HOME;
		}
//		System.out.println("-- 로그인 -- ");
//		System.out.println("아이디 >> ");
//		String memId = ScanUtil.nextLine();
//		System.out.print("비밀번호 >> ");
//		String memPass = ScanUtil.nextLine();
//		
//		List<Object> param = new ArrayList<>();
//		param.add(memId);
//		param.add(memPass);
//		
		Map<String, Object> userInfo = memberDAO.login(getIdPass());
		
		if(userInfo == null) {
			System.out.println("아이디 혹은 비밀번호를 잘못 입력했습니다.");
			return View.HOME;
		}else {
			Controller.sessionStorage.put("userInfo", userInfo);
			Controller.sessionStorage.put("login", true);
			System.out.println(userInfo.get("MEM_NAME") + "님 환영합니다!");
			return View.HOME;
		}
	}
}
