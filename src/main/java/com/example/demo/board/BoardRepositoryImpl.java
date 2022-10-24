package com.example.demo.board;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class BoardRepositoryImpl implements BoardRepository {

	private static Logger logger = LoggerFactory.getLogger(BoardRepositoryImpl.class);

	@Override
	public Board getBoardNoCache(String title) {
		slowQuery(2000);
		return new Board(0, title, title+"`s Contents");
	}

	@Override
	@Cacheable(value = "boardCache", key = "#title")
	public Board getBoardCache(String title) {
		slowQuery(2000);
		return new Board(0, title, title+"`s Contents");
	}

	private void slowQuery(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	@CacheEvict(value = "boardCache", key = "#title")
	public void removeCache(String title) {
		logger.info(title + "의 Cache Clear!");
	}

}
