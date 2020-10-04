package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	
	public int insertBoard(BoardDTO params);
	public BoardDTO selectBoardDetail(Long index);
	public int updateBoard(BoardDTO params);
	public int deleteBoard(Long index);
	public List<BoardDTO> selectBoardList();
	public int selectBoardTotalCount();
}
