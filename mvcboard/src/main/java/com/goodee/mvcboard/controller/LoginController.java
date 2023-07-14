package com.goodee.mvcboard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// Session.getAttributes(names = "로그인 정보키")는 로그인 정보 키가 session 생명주기를 가지고 있어서 불편하다.
public class LoginController {
	
	// 로그인 성공시
	@PostMapping("/login")
	public String login(HttpSession session, // 매개변수 HttpSession session -> 톰캣이 session.getAttribute로 가져오게 된다.
						@RequestParam(name="memberId") String memberId,
						@RequestParam(name="memberPw") String memberPw) {
		// service(memberId, memberPw) -> mapper -> 로그인 성공 유무 반환
		// 모델 생명주기를 session 생명주기로 바꾸는 방법 : model.addAttribute("로그인정보키","로그인정보값");
		session.setAttribute("memberId", memberId); // 로그인 정보 저장
		
		return "redirect:/";
	}
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		// 세션을 종료
		session.invalidate();
		
		return "redirect:/";
	}
}
