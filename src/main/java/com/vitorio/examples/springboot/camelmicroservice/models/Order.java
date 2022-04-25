package com.vitorio.examples.springboot.camelmicroservice.models;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private Date date;
	private Items items;
	private Payment payment;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
