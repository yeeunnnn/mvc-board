package com.goodee.mvcboard.service;

import java.util.Map;

import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.mapper.BoardfileMapper;
import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

import color.Color;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardService {
	//디버깅
	String RESET = Color.ANSI_RESET;
	String GREEN = Color.ANSI_GREEN_BACKGROUND;
		
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private BoardfileMapper boardfileMapper;
	
	// REST API chart 호출...
	public List<Map<String, Object>> getLocalNameListGraph(){
		return boardMapper.selectLocalNameList();
	}
	
	// 게시글 삭제
	public int removeBoard(Board board) {
		return boardMapper.removeBoard(board);
	}
	
	// 게시글 수정
	public int modifyBoard(Board board) {
		return boardMapper.modifyBoard(board);
	}
	
	// 게시글 추가
	public int addBoard(Board board, String path) {
		int row = boardMapper.addBoard(board); // 성공 시 board의 boardNo가 키 값으로 채워짐
		
		List<MultipartFile> fileList = board.getMultipartFile();
		
		// board 입력에 성공 + 첨부된 파일이 1개 이상이고 + 그 크기가 1보다 클때
		if(row == 1 && fileList != null && fileList.size() >= 1) {
			
			int boardNo = board.getBoardNo();
			
			for(MultipartFile mf : fileList) { // 첨부된 파일의 개수만큼 반복
				if(mf.getSize() > 0) {
					Boardfile bf = new Boardfile();
					bf.setBoardNo(boardNo); // 부모 키값
					bf.setOriginFilename(mf.getOriginalFilename()); // 파일 원본이름
					bf.setFilesize(mf.getSize()); // 파일 사이즈
					bf.setFiletype(mf.getContentType()); // 파일타입
					
				// 저장될 파일 이름
					// 확장자 (고유성을 지킬 수 있으며, 일반적인 파일 이름 형식을 준수할 수 있음)
					String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf(".")); //마지막 점(.)의 위치부터 끝까지 자르는 코드
					
					// 새로운 이름 구하기
					// randomUUID() = 동일한 UUID가 생성될 가능성을 줄이기 위해 random 이용
					// random.toString() = 만들어진 randomUUID를 문자열로 변환
					// replace("-","") = 하이픈을 제거
					bf.setSaveFilename(UUID.randomUUID().toString().replace("-","") + ext);
					
				// boardFile 테이블에 저장
					boardfileMapper.addBoardfile(bf);
					
				// 파일 저장(저장 위치 필요 -> path 변수)
					// path 위치에 저장파일 이름으로 빈 파일을 생성(0byte)
					File f = new File(path+bf.getSaveFilename());
					
					try {
						mf.transferTo(f); // 빈 파일에 첨부된 파일의 스트림을 주입
					} catch(IllegalStateException | IOException e) {
						
						e.printStackTrace();
						// 트랜잭션 작동을 위해 예외(try..catch를 강용하지 않는 예외 ex: RuntimeException)를 발생하는 게 필요하다.
						throw new RuntimeException();
					}
				}
			}
		}
		return row; // 0 또는 1을 반환
	}
	
	// 지역이름만 출력하는 메소드
	public List<Map<String, Object>> getLocalNameList() {
	    List<Map<String, Object>> localNameList = boardMapper.selectLocalNameList(); // 지역 이름 목록 가져오기
	    return localNameList;
	}
	
	// 전체 게시글 조회
	// 메소드 위에 transaction
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String localName) {
		
		// service layer 역할 1 : controller가 넘겨준 매개값을 DAO 매개값에 맞게 가공
		int beginRow = (currentPage-1) * rowPerPage;
		
		// 반환값 1
		List<Map<String, Object>> localNameList = boardMapper.selectLocalNameList();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("localName", localName);
		
		// 반환값 2
		List<Board> boardList = boardMapper.selectBoardListByPage(paramMap);
		
		// 마지막으로, int 형으로 받아 10으로 나눠줌(페이징)
		int boardCount = boardMapper.selectBoardCount(localName);
		
		// service layer 역할 2 : DAO에서 반환받은 값을 controller 반환값에 맞게 가공 -> controller에 반환
		int lastPage = boardCount / rowPerPage;
		if(boardCount % rowPerPage != 0) {
			lastPage += 1;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("localNameList", localNameList);
		resultMap.put("boardList", boardList);
		resultMap.put("lastPage", lastPage);
		resultMap.put("localName", localName);
		
		return resultMap;
	}
	
	// 하나의 게시글 조회
	public Map<String, Object> getSelectBoardOne(int boardNo) {
	    Board boardOne = boardMapper.selectBoardOne(boardNo); // Board 상세 정보 가져오기
	    List<Map<String, Object>> selectSaveFile = boardfileMapper.selectSaveFile(boardNo); // 저장된 파일 가져오기
	    
	    // resultMap에 담아 사용하도록 함.
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("boardOne", boardOne);
	    resultMap.put("selectSaveFile", selectSaveFile);
	    
	    return resultMap;
	}
}
