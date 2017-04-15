package com.aizant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class BarCodeController {

	
	@RequestMapping(value = "/barcode", method = RequestMethod.POST)
	public @ResponseBody String scanCode(@RequestParam String scannedCode) {
		return "";
	}
}
