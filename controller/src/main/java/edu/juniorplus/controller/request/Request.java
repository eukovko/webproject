package edu.juniorplus.controller.request;

public final class Request {

	private final Operation operation;
	private final String payload;

	public Request(Operation operation, String payload) {
		this.operation = operation;
		this.payload = payload;
	}

	public Operation getOperation() {
		return operation;
	}

	public String getPayload() {
		return payload;
	}
}
