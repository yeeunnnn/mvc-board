package com.goodee.mvcboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Board;

@Mapper
public interface BoardMapper { // 추상 메소드
	
// DAO와 연결
	// 게시글 삭제
	int removeBoard(Board board);
	// 게시글 수정
	int modifyBoard(Board board);
	// 게시글 추가
	int addBoard(Board board);
	
	// xml 파일 sql 파일로 할 수 있음
	List<Map<String, Object>> selectLocalNameList(); // local_name 컬럼과 count() 반환
	
	// mybatis 메서드는 매개값을 하나만 허용
	// param : Map<String, Object> map --> int beginRow, int rowPerPage
	List<Board> selectBoardListByPage(Map<String, Object> map);
	
	// 전체 행의 수
	int selectBoardCount(String localName);
	
	// 하나의 게시글 조회
	Board selectBoardOne(int BoardNo);
}
