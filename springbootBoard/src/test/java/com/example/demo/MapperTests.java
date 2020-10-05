package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mapper.BoardMapper;

@SpringBootTest
public class MapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsert() {
		
	}
	
}
