package com.vitorio.examples.springboot.camelmicroservice.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {
	private String format;
	private int amount;
	private Book book;

	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Item [format=" + format + ", amount=" + amount + ", book=" + book + "]";
	}
}
