package com.aizant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class BarCodeController {
	@RequestMapping(value = "/barcode", method = RequestMethod.POST)
	public @ResponseBody String scanCode(@RequestBody String scannedCode) {
		System.out.println("SCANNED CODE" + scannedCode);
		return "";
	}
}
