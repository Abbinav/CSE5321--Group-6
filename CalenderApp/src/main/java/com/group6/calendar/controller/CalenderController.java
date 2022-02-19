package com.group6.calendar.controller;

import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.group6.calendar.dao.*;
import com.group6.calendar.model.*;

@Controller
public class CalenderController {

	@Autowired
	EventRepo repo;
	
	private static final String DATE_FORMATTER = "yyyy-MM-dd";
	
	@GetMapping("/calendar")
	public String greeting(Model model) {
		model.addAttribute("something", "Abbi");
		ModelMap modelData = new ModelMap();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		model.addAttribute("date", dateFormat.format(date));
		model.addAttribute("title", "test");
		List<Activity> activityData = new ArrayList<>();
		activityData = repo.findAll();
		//modelData.put("activityData", activityData);
		
		if(activityData != null && activityData.size() > 0)
		{
			for(Activity activity: activityData)
			{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
				String formattedDate = activity.getStartTime().format(formatter);
				//String dat1=output.format(activity.getStartTime());
				model.addAttribute("date", formattedDate);
				model.addAttribute("title", activity.getActivityName());				
				modelData.put("title", activity.getActivityName());
				modelData.put("date", formattedDate);
				System.out.println("date" + formattedDate);
				System.out.println("name" + activity.getActivityName());
			}
			model.addAttribute("modelData", activityData);
		}
		
		
		return "Calender";
	}


	@RequestMapping("/CreateEvent")
	public String gre1ting(Model model)  {
		//,@RequestParam(value="dateEvent") String dateEvent,@RequestParam(value="nameEvent") String nameEvent
		//System.out.println("Inside eventsToday / dateEvent :" + dateEvent);
		//System.out.println("Inside eventsToday / nameEvent :" + nameEvent);


		return "CreateEvent";
	}



	@PostMapping("/calendar/{id}")
	public String gre11ting(Model model,@RequestParam(value="dateEvent") String dateEvent,@RequestParam(value="endDateEvent") String endDateEvent,@RequestParam(value="nameEvent") String nameEvent)  {
		//,@RequestParam(value="dateEvent") String dateEvent,@RequestParam(value="nameEvent") String nameEvent		
		Activity activityData = new Activity();
		
		System.out.println("Inside eventsToday / dateEvent :" + dateEvent);
		System.out.println("Inside eventsToday / dateEvent :" + endDateEvent);

		System.out.println("Inside eventsToday / nameEvent :" + nameEvent);
		if((dateEvent != null || dateEvent != "") && (nameEvent !=null || nameEvent !="") && (endDateEvent != null || endDateEvent != ""))
		{
			activityData.setActivityName(nameEvent);
			DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime startTime = LocalDateTime.parse(dateEvent, inputFormat);
			LocalDateTime endTime = LocalDateTime.parse(endDateEvent, inputFormat);
			activityData.setStartTime(startTime);;
			activityData.setEndTime(endTime);
			repo.save(activityData);
		}

		
		return "redirect:/calendar";
	}

//	@GetMapping("/calendar/date/{date1}/title/{title}")
//	public String greeeting(Model model,@PathVariable("date1") String date1,@PathVariable("title") String title) throws ParseException {
//		model.addAttribute("something", "Abbi");
//		SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
//
//		Date d1 = null;
//		d1=input.parse(date1);
//		String dat1=output.format(d1);
//
//		model.addAttribute("date", dat1);
//		model.addAttribute("title", title);		
//		return "Calender";
//	}


}
