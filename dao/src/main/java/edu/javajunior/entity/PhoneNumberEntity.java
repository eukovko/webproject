package edu.javajunior.entity;

public final class PhoneNumberEntity extends Entity {

	private final Long userId;
	private final String phoneNumber;

	public PhoneNumberEntity(String phoneNumber) {
		super(null);
		this.userId = null;
		this.phoneNumber = phoneNumber;
	}

	public PhoneNumberEntity(Long id, Long userId, String phoneNumber) {
		super(id);
		this.userId = userId;
		this.phoneNumber = phoneNumber;
	}

	public PhoneNumberEntity withId(Long id) {
		return new PhoneNumberEntity(id, userId, phoneNumber);
	}

	public PhoneNumberEntity withUserId(Long userId) {
		return new PhoneNumberEntity(getId(), userId, phoneNumber);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Long getUserId() {
		return userId;
	}
}
