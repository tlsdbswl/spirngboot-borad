package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.example.demo.domain.BoardDTO;
import com.example.demo.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
public class MapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");
		
		int result = boardMapper.insertBoard(params);
		System.out.println("결과는"+result);
	}
	
	@Test
	public void testOfSelectDetail() {
		BoardDTO board = boardMapper.selectBoardDetail((long)1);
		try {
			String boardJson = new ObjectMapper().writeValueAsString(board);
			
			System.out.println("===============");
			System.out.println(boardJson);
			System.out.println("===============");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목 수정");
		params.setContent("1번 시글 내용 수정");
		params.setWriter("홍길동");
		params.setIdx((long)1);
		
		int result = boardMapper.updateBoard(params);
		if(result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long)1);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				
				System.out.println("===============");
				System.out.println(boardJson);
				System.out.println("===============");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void deleteOfUpdate() {
		int result = boardMapper.deleteBoard((long)1);
		if(result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long)1);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				
				System.out.println("===============");
				System.out.println(boardJson);
				System.out.println("===============");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testMultipleInsert() {
		for(int i=2; i<=50; i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle(i+"번 게시글 제목");
			params.setContent(i+"번 게시글 내용");
			params.setWriter(i+"번 게시글 작성자");
			boardMapper.insertBoard(params);
		}
	}
	
	//1번 게시물은 위에서 delete 테스트 해서 지워져서 안 나옴
	@Test
	public void testSelectList() {
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		if(boardTotalCount > 0) {
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			if(CollectionUtils.isEmpty(boardList) == false){
				for(BoardDTO board : boardList) {
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
				}
			}
		}
		
	}
}
