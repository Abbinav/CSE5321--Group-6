package com.group6.calendar.model;

import java.time.LocalDateTime;

import org.springframework.ui.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calender {

	Long id;	
	String activityName;	
	LocalDateTime startTime;	
	LocalDateTime endTime;
	
//	String getCalender(Model model)
//	{
//		model.addAttribute("something", "Abbi is my love");
//		return "calender";
//	}
}
