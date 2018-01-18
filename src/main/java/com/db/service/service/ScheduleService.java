package com.db.service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.service.dao.RuleDAO;
import com.db.service.dao.ScheduleDAO;
import com.db.service.dao.ScheduleTypeDAO;
import com.db.service.dto.ScheduleDTO;
import com.db.service.entity.Rule;
import com.db.service.entity.Schedule;
import com.db.service.entity.ScheduleType;

@Component
public class ScheduleService {
	
	@Autowired
	ScheduleDAO scheduleRepo;
	
	@Autowired
	ScheduleTypeDAO scheduleTypeRepo;
	
	@Autowired
	RuleDAO ruleRepo;
	
	
	private ScheduleType getScheduleTypeByValue(String value) throws Exception{
		List<ScheduleType> scheduleTypes = scheduleTypeRepo.findScheduleTypeFromValue(value);
		if(scheduleTypes != null && scheduleTypes.size() == 1){
			return scheduleTypes.get(0);
		}
		else{
			throw new Exception();
		}
	}
	
	
	public List<ScheduleDTO> getSchedules(){
		List<ScheduleDTO> resList = new ArrayList<>();
		try{
			List<Schedule> schedules = scheduleRepo.findAll();
			if(schedules != null && schedules.size()>0){
				for( int i=0; i<schedules.size(); i++){
					resList.add(new ScheduleDTO(schedules.get(i)));
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	public ScheduleDTO getScheduleById(String scheduleIdStr){
		ScheduleDTO scheduleDTO = null;
		try{
			int scheduleId = Integer.parseInt(scheduleIdStr);
			Schedule schedule = scheduleRepo.findOne(scheduleId);
			if(schedule != null){
				scheduleDTO = new ScheduleDTO(schedule);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return scheduleDTO;
	}
	
	public List<ScheduleDTO> getSchedulesByRuleId(String ruleIdStr){
		List<ScheduleDTO> resList = new ArrayList<>();
		try{
			int ruleId = Integer.parseInt(ruleIdStr);
			Rule rule = ruleRepo.findOne(ruleId);
			if(rule != null){
				List<Schedule> schedules = rule.getSchedules();
				if(schedules != null && schedules.size() > 0){
					for (Schedule schedule : schedules) {
						resList.add(new ScheduleDTO(schedule));
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	public boolean saveSchedule(String ruleIdStr, ScheduleDTO scheduleDTO){
		boolean res = false;
		try{
			int ruleId = Integer.parseInt(ruleIdStr);
			Rule rule = ruleRepo.findOne(ruleId);
			String scheduleTypeStr = scheduleDTO.getScheduleType();
			ScheduleType scheduleType = getScheduleTypeByValue(scheduleTypeStr);
			
			if(rule != null && scheduleType != null){
				scheduleRepo.save(scheduleDTO.toSchedule(rule, scheduleType));
				res = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public boolean updateSchedule(String ruleIdStr, String scheduleIdStr, ScheduleDTO scheduleDTO){
		boolean res = false;
		try{
			int ruleId = Integer.parseInt(ruleIdStr);
			int id = Integer.parseInt(scheduleIdStr);		
			int date = scheduleDTO.getDate();
			boolean isActive = scheduleDTO.getIsActive();
			int hourOfDay =  scheduleDTO.getHourOfDay();
			int repeatEvery = scheduleDTO.getRepeatEvery();
			Date lastUpdated = new Date();
			String scheduleTypeStr = scheduleDTO.getScheduleType();
			ScheduleType scheduleType = getScheduleTypeByValue(scheduleTypeStr);
				
							
			Schedule schedule = scheduleRepo.findOne(id);
			if(schedule != null && schedule.getRule().getId() == ruleId && scheduleType != null){
				schedule.setDate(date);
				schedule.setIsActive(isActive);
				schedule.setHourOfDay(hourOfDay);
				schedule.setRepeatEvery(repeatEvery);
				schedule.setLastUpdated(lastUpdated);
				schedule.setScheduleType(scheduleType);
				scheduleRepo.save(schedule);
				res = true;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
}
