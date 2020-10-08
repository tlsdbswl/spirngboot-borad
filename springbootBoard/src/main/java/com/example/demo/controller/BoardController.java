package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.BoardDTO;
import com.example.demo.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = "/board/write.do")
	public String openBoardWriter(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		if(idx == null) {
			model.addAttribute("board", new BoardDTO());
		} else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if(board == null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board", board);
		}
	return "board/write";
	}
	
	@PostMapping(value = "/board/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if(isRegistered == false) {
				//게시글 등록에 실패했다는 메시지 출력 
			}
		} catch(DataAccessException e){
			//디비 처리 과정에 문제가 발생했다는 메시지 출력 
		} catch(Exception e){
			//시스템 문제가 발생했 메시지 출력
			}
		return "redirect:/board/list.do";
	}
}
