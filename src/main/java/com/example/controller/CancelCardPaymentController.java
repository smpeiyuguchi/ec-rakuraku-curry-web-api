package com.example.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.RequestOrderInfo;
import com.example.domain.ResponseCardValidity;

@RequestMapping("/credit-card")
@RestController
public class CancelCardPaymentController {

	@RequestMapping(value = "/cancel", method = { RequestMethod.POST })
	public ResponseCardValidity cancelPayment(@RequestBody RequestOrderInfo orderInfo) {
		ResponseCardValidity cardValidity = new ResponseCardValidity();
		cardValidity.setStatus("success");
		cardValidity.setMessage("canceld");
		cardValidity.setError_code("E-00");
		return cardValidity;
	}

}
