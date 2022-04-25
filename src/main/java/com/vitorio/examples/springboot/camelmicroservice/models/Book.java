package com.vitorio.examples.springboot.camelmicroservice.models;

public class Book {

	String code;
	String title;
	String shortTitle;	
	String authorName;
	double priceEbook;
	double pricePrinted;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public double getPriceEbook() {
		return priceEbook;
	}
	public void setPriceEbook(double priceEbook) {
		this.priceEbook = priceEbook;
	}
	public double getPricePrinted() {
		return pricePrinted;
	}
	public void setPricePrinted(double pricePrinted) {
		this.pricePrinted = pricePrinted;
	}

	@Override
	public String toString() {
		return "Book [code=" + code + ", title=" + title + ", shortTitle=" + shortTitle + ", authorName=" + authorName
				+ ", priceEbook=" + priceEbook + ", pricePrinted=" + pricePrinted + "]";
	}
}
