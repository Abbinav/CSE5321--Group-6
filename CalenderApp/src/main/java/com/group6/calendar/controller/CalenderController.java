package com.group6.calendar.controller;

import java.text.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.group6.calendar.dao.*;
import com.group6.calendar.model.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CalenderController {

	@Autowired
	EventRepo repo;
	String errMesg="";
	private static final String DATE_FORMATTER = "yyyy-MM-dd";
	
	@GetMapping("/calendar")
	public String getActivity(Model model) {
		model.addAttribute("something", "Abbi");
		ModelMap modelData = new ModelMap();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		model.addAttribute("date", dateFormat.format(date));
		model.addAttribute("title", "test");
		List<Activity> activityData = new ArrayList<>();
		activityData = repo.findAll();
		List<Activity> notificationData = new ArrayList<>();

		DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(DATE_FORMATTER);
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime tomorrow = today.plusDays(1);
		String todayFormattedDate = today.format(inputFormat);
		String tomorrowFormattedDate = tomorrow.format(inputFormat);
		LocalDateTime todayAt9 = LocalDate.now().atTime(4, 0);
if(errMesg.length()>0) {
	model.addAttribute("errMsg", errMesg);
}

		
		if(activityData != null && activityData.size() > 0)
		{
			notificationData = activityData.stream().filter(a -> 
			(a.getStartTime().format(inputFormat).contains(todayFormattedDate) 
					|| a.getEndTime().format(inputFormat).contains(tomorrowFormattedDate))).collect(Collectors.toList());
			
			
			for(Activity activity: activityData)
			{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
				String formattedDate = activity.getStartTime().format(formatter);			
				//String dat1=output.format(activity.getStartTime());
				model.addAttribute("date", formattedDate);
				model.addAttribute("title", activity.getActivityName());				
				modelData.put("title", activity.getActivityName());
				modelData.put("date", formattedDate);
				//System.out.println("notificationdata" + notificationData);
				System.out.println("date" + formattedDate);
				System.out.println("name" + activity.getActivityName());
			}
			model.addAttribute("modelData", activityData);
			if(today.isAfter(todayAt9))
				model.addAttribute("notificationData", notificationData);
		}
		
		
		return "Calender";
	}


	@RequestMapping("/CreateEvent")
	public String createEvent(Model model)  {		
		return "CreateEvent";
	}



	@PostMapping("/calendar/{id}")
	public String createEvent(Model model, @RequestParam(value="dateEvent") String dateEvent, @RequestParam(value="endDateEvent") String endDateEvent, @RequestParam(value="nameEvent") String nameEvent,@RequestParam(value="yes") String rdYes)  {
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


			String startTimeFormattedDate = startTime.format(inputFormat);
			String endTimeFormattedDate = endTime.format(inputFormat);
			System.out.println("start" + startTimeFormattedDate);
			System.out.println("end" + endTimeFormattedDate);

			List<Activity> Data1 = new ArrayList<>();
			List<Activity> result = new ArrayList<>();

			System.out.println("yes "+ rdYes);
			//System.out.println("no"+ rdno);

			model.addAttribute("errMsg","test message");


			Data1 = repo.findAll();
			if(Data1 != null && Data1.size() > 0) {
				result = Data1.stream().filter(a ->
						(a.getStartTime().format(inputFormat).contains(startTimeFormattedDate)
								|| a.getEndTime().format(inputFormat).contains(endTimeFormattedDate))).collect(Collectors.toList());
			}
			if(startTime.isBefore(endTime) || startTime.equals(endTime)) {
				activityData.setStartTime(startTime);
				activityData.setEndTime(endTime);
				System.out.println("test1");
				if(result.size()==0) {
					System.out.println("test2");

						repo.save(activityData);

				}
				else
				{
					if(rdYes.contains("yes"))
					{
						errMesg="";
						model.addAttribute("errMsg","");
						System.out.println("inside yes");
						repo.save(activityData);
					}
					else
					{
						errMesg="There is a conflict with the selected time ";
						System.out.println("err"+ errMesg);
						model.addAttribute("errMsg","There is a conflict with the selected time");
					}

				}
			}
			else
			{
				System.out.println("start cannot be greater");
				errMesg="Start date cannot be greater";

			}

			//repo.save(activityData);
		}

		//return "calendar";
		return "redirect:/calendar";
	}



}
