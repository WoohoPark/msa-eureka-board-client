package com.example.demo.board;

public interface BoardRepository {

	Board getBoardCache(String title);

	void removeCache(String title);

	Board getBoardNoCache(String title);
}
