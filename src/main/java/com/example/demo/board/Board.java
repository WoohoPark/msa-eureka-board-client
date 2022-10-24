package com.example.demo.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Board {

	private int id;

	private String title;

	private String context;

	public Board(int id, String title, String context) {
		this.id=id;
		this.title=title;
		this.context=context;
	}

	public int getId() {
		return id;
	}

	public String getContext() {
		return context;
	}

	public String getTitle() {
		return title;
	}
}
