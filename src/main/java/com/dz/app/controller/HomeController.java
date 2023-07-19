package com.dz.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping(value = "/",method = RequestMethod.GET)
	@ResponseBody
	public String isAlive() {
		
//		dev tools test 
		
		int a=30;
		int b=20;
		System.out.println("sum "+(a+b));
				
		return "i am alive..!";
	}
}
