package com.group6.calendar.controller;

import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalenderController {

	@GetMapping("/calendar")
	public String greeting(Model model) {
		model.addAttribute("something", "Abbi");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		model.addAttribute("date", dateFormat.format(date));
		model.addAttribute("title", "test");
		return "Calender";
	}


	@RequestMapping("/CreateEvent")
	public String gre1ting(Model model)  {
		//,@RequestParam(value="dateEvent") String dateEvent,@RequestParam(value="nameEvent") String nameEvent
		//System.out.println("Inside eventsToday / dateEvent :" + dateEvent);
		//System.out.println("Inside eventsToday / nameEvent :" + nameEvent);


		return "CreateEvent";
	}



	@RequestMapping("/Calendar1/{id}")
	public String gre11ting(Model model,@RequestParam(value="dateEvent") String dateEvent,@RequestParam(value="nameEvent") String nameEvent)  {
		//,@RequestParam(value="dateEvent") String dateEvent,@RequestParam(value="nameEvent") String nameEvent
		System.out.println("Inside eventsToday / dateEvent :" + dateEvent);
		System.out.println("Inside eventsToday / nameEvent :" + nameEvent);


		return "redirect:/calendar/date/"+dateEvent+"/title/"+nameEvent;
	}

	@GetMapping("/calendar/date/{date1}/title/{title}")
	public String greeeting(Model model,@PathVariable("date1") String date1,@PathVariable("title") String title) throws ParseException {
		model.addAttribute("something", "Abbi");
		SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

		Date d1 = null;
		d1=input.parse(date1);
		String dat1=output.format(d1);

		model.addAttribute("date", dat1);
		model.addAttribute("title", title);
		return "Calender";
	}


}
