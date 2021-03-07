package edu.juniorplus.controller.request;

import lombok.Data;

@Data
public class Request {
	private Operation operation;
	private String payload;
}
