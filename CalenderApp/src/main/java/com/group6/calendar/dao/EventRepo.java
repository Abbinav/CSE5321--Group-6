package com.group6.calendar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group6.calendar.model.*;

public interface EventRepo extends JpaRepository<Activity, Integer>{


}
