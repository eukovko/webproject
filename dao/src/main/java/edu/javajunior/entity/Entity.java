package edu.javajunior.entity;

public abstract class Entity {

	private final Long id;

	protected Entity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
