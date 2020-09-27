package com.home.aspect.DTO;

public class BasicTestDTO {

	private String cardNumber;
	private String  cvn;
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCvn() {
		return cvn;
	}
	public void setCvn(String cvn) {
		this.cvn = cvn;
	}
	@Override
	public String toString() {
		return "BasicTestDTO [cardNumber=" + cardNumber + ", cvn=" + cvn + "]";
	}
	
	
}
