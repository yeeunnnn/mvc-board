package com.goodee.mvcboard.restapi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.mvcboard.service.BoardService;

@CrossOrigin
@RestController // request body가 저절로 붙는다.
public class BoardRest {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/rest/localNameListGraph")
	public List<Map<String, Object>> getLocalNameListGraph(){
		return boardService.getLocalNameList(); //@CrossOrigin이 JSON 데이터로 바꾸어줌.
	}
}
