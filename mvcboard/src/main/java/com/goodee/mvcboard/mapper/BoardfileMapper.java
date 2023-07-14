package com.goodee.mvcboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Boardfile;

@Mapper
public interface BoardfileMapper {
	
	// 파일 추가
	int addBoardfile(Boardfile boardfile); //매개값으로 Boardfile vo
	
	// 파일 이름 가져오기
	List<Map<String, Object>> selectSaveFile(int boardNo);
}
