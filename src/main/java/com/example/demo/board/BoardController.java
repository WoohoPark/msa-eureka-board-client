package com.example.demo.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableCaching
public class BoardController {

	private final BoardRepository boardRepository;

	public BoardController(BoardRepository boardRepository){
		this.boardRepository = boardRepository;
	}

	@GetMapping("/getPort")
	public String getPort(@Value("${server.port}") String port) {
		return "Board 서비스의 기본 동작 Port: {" + port + "}";
	}

	@GetMapping("/noCache/board/{title}")
	public Board getBoardNoCache(@PathVariable String title){
		long start = System.currentTimeMillis(); // 수행시간 측정
		Board board = boardRepository.getBoardNoCache(title);
		long end = System.currentTimeMillis();
		System.out.println((start-end)/1000+"초 걸림");
		return board;
	}

	@GetMapping("/cache/board/{title}")
	public ResponseEntity<Board> getBoardCache(@PathVariable String title){
		long start = System.currentTimeMillis(); // 수행시간 측정
		Board board = boardRepository.getBoardCache(title);
		long end = System.currentTimeMillis();
		System.out.println((start-end)/1000+"초 걸림");
		return ResponseEntity.status(HttpStatus.OK).body(board);
	}

	@DeleteMapping("/cache/board/{title}")
	public void removeCache(@PathVariable String title){
		boardRepository.removeCache(title);
	}

}
