package com.db.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.service.dto.ScheduleDTO;
import com.db.service.service.ScheduleService;

@RestController
@RequestMapping("api")
public class ScheduleController {
	@Autowired
	ScheduleService scheduleService;
	
	@RequestMapping(value="/schedules", method=RequestMethod.GET)
	public List<ScheduleDTO> getSchedules(){
		return scheduleService.getSchedules();
	}
	
	@RequestMapping(value="/schedules/{schedule_id}", method=RequestMethod.GET)
	public ScheduleDTO getScheduleById(@PathVariable("schedule_id") String scheduleIdStr){
		return scheduleService.getScheduleById(scheduleIdStr);
	}
	
	@RequestMapping(value="/rules/{rule_id}/schedules", method=RequestMethod.GET)
	public List<ScheduleDTO> getSchedulesByRuleId(@PathVariable("rule_id") String ruleIdStr){
		return scheduleService.getSchedulesByRuleId(ruleIdStr);
	}
	
	@RequestMapping(value="/rules/{rule_id}/schedules", method=RequestMethod.POST)
	public boolean saveSchedule(@PathVariable("rule_id") String ruleIdStr, @RequestBody(required=false) ScheduleDTO scheduleDTO){
		return scheduleService.saveSchedule(ruleIdStr, scheduleDTO);
	}
	
	@RequestMapping(value="/rules/{rule_id}/schedules/{schedule_id}", method=RequestMethod.PUT)
	public boolean updateSchedule(@PathVariable("rule_id") String ruleIdStr, 
			@PathVariable("schedule_id") String scheduleIdStr, 
			@RequestBody(required=false) ScheduleDTO scheduleDTO){
		return scheduleService.updateSchedule(ruleIdStr, scheduleIdStr ,scheduleDTO);
	}
	
	
	
}
