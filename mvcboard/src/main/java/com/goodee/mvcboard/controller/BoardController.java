package com.goodee.mvcboard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcboard.service.BoardService;
import com.goodee.mvcboard.vo.Board;

import color.Color;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	//디버깅
	String RESET = Color.ANSI_RESET;
	String GREEN = Color.ANSI_GREEN_BACKGROUND;

	@Autowired
	private BoardService boardService;

/* 추가 */
	// 게시글 추가 폼
	@GetMapping("/board/addBoard")
	public String addBoard(Model model) { 
		List<Map<String, Object>> localNameList = boardService.getLocalNameList();
		model.addAttribute("localNameList", localNameList);
		return "/board/addBoard";
	}
	// 게시글 추가 액션
	@PostMapping("/board/addBoard")
	public String addBoard(HttpServletRequest request, Board board) {
		// request api를 직접 호출하기 위해 매개값으로 request 객체를 받음
		String path = request.getServletContext().getRealPath("/upload/"); //직접 실제 위치(경로)를 구해서 service에 넘겨주는 api
		int row = boardService.addBoard(board, path);
		log.debug(GREEN+"addBoard row : "+row+RESET); // debug 용도. static log 필드(5개)
		return "redirect:/board/boardList";	// 대소문자까지 일치하도록 작성
	}
	
/* 수정 */
	// 게시글 수정 폼
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model,
							//@RequestParam(name = "memberId", required = false) String memberId,
							@RequestParam(name = "boardNo", required = false) int boardNo) {
		Map<String, Object> resultMap = boardService.getSelectBoardOne(boardNo);
		model.addAttribute("boardOne", resultMap.get("boardOne"));
		//model.addAttribute("memberid", memberId);
		//model.addAttribute("boardNo", boardNo);
		return "/board/modifyBoard";
	}
	// 게시글 수정 액션
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(Board board) { 
		int row = boardService.modifyBoard(board);
		log.debug(GREEN+"modify row : "+row+RESET);
		return "redirect:/board/boardOne";
	}
	
/* 삭제 */
	// 게시글 삭제 폼
	@GetMapping("/board/removeBoard")
	public String removeBoard(Model model,
							//@RequestParam(name = "memberId", required = false) String memberId,
							@RequestParam(name = "boardNo") int boardNo) {
		//model.addAttribute("memberid", memberId);
		// One에서 boardNo를 가져와서 model에 넣어주기
		Map<String, Object> resultMap = boardService.getSelectBoardOne(boardNo);
		model.addAttribute("boardOne", resultMap.get("boardOne"));
		return "/board/removeBoard";
	}
	// 게시글 삭제 액션
	@PostMapping("/board/removeBoard")
	public String removeBoard(Board board) { 
		int row = boardService.removeBoard(board);
		log.debug(GREEN+"remove row : "+row+RESET);
		return "redirect:/board/boardList";
	}
	
/* 조회 */
	// 전체 게시글 조회
	@GetMapping("/board/boardList")
	public String boardList(Model model,
							@RequestParam(name = "currentPage", defaultValue = "1" ) int currentPage, // request를 대신하며 형변환을 하지 않아도 됨.
							@RequestParam(name = "rowPerPage", defaultValue = "10" ) int rowPerPage,
							@RequestParam(name = "localName", required = false) String localName) { // Map이 아닌, spring의 model이라는 타입을 사용. request/session과 생명주기를 같이 할 수 있음
		// null 값이 넘어오면 안됨
		Map<String, Object> resultMap = boardService.getBoardList(currentPage, rowPerPage, localName); // 두개의 리스트가 들어있음.
		
		// view로 넘길때는 다시 분리해서
		model.addAttribute("localNameList", resultMap.get("localNameList"));
		model.addAttribute("boardList", resultMap.get("boardList"));
		model.addAttribute("localName", localName);
		
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		
		return "/board/boardList";
	}
	// 하나의 게시글 조회
	@GetMapping("/board/boardOne")
	public String boardList(Model model,
							@RequestParam(name = "boardNo") int boardNo) {
		Map<String, Object> resultMap = boardService.getSelectBoardOne(boardNo);
		
		// view로 넘길때는 다시 분리해서
		model.addAttribute("boardOne", resultMap.get("boardOne"));
		System.out.println(GREEN + resultMap.get("boardOne") != null ? 1 : 0 + RESET);
		model.addAttribute("saveFilename", resultMap.get("selectSaveFile"));
		System.out.println(GREEN + resultMap.get("selectSaveFile")!=null? 1 : 0 + RESET);
		return "/board/boardOne";
	}
	
}
