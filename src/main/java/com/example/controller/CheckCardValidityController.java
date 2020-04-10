package com.example.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.RequestCardInfo;
import com.example.domain.ResponseCardValidity;

@RequestMapping("/credit-card")
@RestController // JSON形式でリターンする
public class CheckCardValidityController {

	@RequestMapping(value = "/payment", method = { RequestMethod.POST })
	// @RequestBodyを付けることで、JSONのキーにあたる部分と、オブジェクトのプロパティの変数名が一致するものに値が注入される
	// パラメータで受け取る場合は@RequestParamでパラメータ名を指定しないと415エラーとなる
	public ResponseCardValidity checkCardValidity(@RequestBody RequestCardInfo cardInfo) {
		ResponseCardValidity cardValidity = new ResponseCardValidity();

		try {
			LocalDate orderDate = LocalDate.now();
			LocalDate cardExpDate = LocalDate.of(Integer.parseInt(cardInfo.getCard_exp_year()),
					Integer.parseInt(cardInfo.getCard_exp_month()), 1);
			cardExpDate = cardExpDate.with(TemporalAdjusters.lastDayOfMonth());
			if (cardExpDate.isBefore(orderDate)) {
				cardValidity.setStatus("error");
				cardValidity.setMessage("The card is expired.");
				cardValidity.setError_code("E-01");
			} else if (!(cardInfo.getCard_cvv().equals("123"))) {
				cardValidity.setStatus("error");
				cardValidity.setMessage("The card information is incorrect.");
				cardValidity.setError_code("E-02");
			} else {
				cardValidity.setStatus("success");
				cardValidity.setMessage("OK");
				cardValidity.setError_code("E-00");
			}
		} catch (NumberFormatException ex) {
			cardValidity.setStatus("error");
			cardValidity.setMessage("Error");
			cardValidity.setError_code("E-03");
		}
		return cardValidity;
	}

}
