package com.example.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RequestOrderInfo implements Serializable {

	private String order_number;

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

}
