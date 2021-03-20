package edu.juniorplus.domain;

import java.util.regex.Pattern;

// TODO: 3/14/2021 Validate with setters (mutable)
public class PhoneNumber {

    private static final String PHONE_NUMBER_REGEX = "^[+][0-9]{3}[(][0-9]{1,2}[)][0-9]{3}[-][0-9]{2}[-][0-9]{2}$";
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);
    private static final String FORMAT_ERROR_MESSAGE = "Phone number should be represented in the following format " +
            "+[3 digit code]([2 digit operator code])[3 digits]-[2 digits]-[2 digits]";

    // TODO: 3/14/2021 Split into country code, operator code and number
    private String phoneNumber;
    private String countryCod;
    private String operatorCode;
    private String number;

    public PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber) {
        if (!checkFormat(phoneNumber)) {
            throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
        }
        String[] phoneElements = phoneNumber.split("\\D+");
        this.countryCod = phoneElements[1];
        this.operatorCode = phoneElements[2];
        this.number = String.format("%s-%s-%s", phoneElements[3], phoneElements[4], phoneElements[5]);
        this.phoneNumber = String.format("+%s(%s)%s", countryCod, operatorCode, number);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!checkFormat(phoneNumber)) {
            throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
        }
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCod() {
        return countryCod;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public String getNumber() {
        return number;
    }

    public static boolean checkFormat(String phoneNumber) {
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }

    @Override
    public String toString() {
        return phoneNumber;
    }
}
