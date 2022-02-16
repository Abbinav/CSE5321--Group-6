package com.group6.calendar.controller;

import java.text.*;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CalenderController {
	
	@GetMapping("/calendar")
	public String greeting(Model model) {
		model.addAttribute("something", "Abbi");
		Calendar calendar = Calendar.getInstance();			
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();		
		model.addAttribute("date", dateFormat.format(date));
		return "Calender";
	}



}
