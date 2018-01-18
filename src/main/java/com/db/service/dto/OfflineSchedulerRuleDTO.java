package com.db.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.db.service.entity.Rule;
import com.db.service.entity.Schedule;
import com.db.service.utility.DateTimeAdapter;

public class OfflineSchedulerRuleDTO {
	public  int id;
	public String status;
	public String start_time;
	public String end_time;
	public String last_updated;
	public String last_triggered;
	public String last_executed;
	public List<OfflineSchedulerScheduleDTO> schedules;
	
	
	public OfflineSchedulerRuleDTO(Rule rule){
		this.id = rule.getId();
		this.status = rule.getRuleStatus().getValue();
		this.start_time = DateTimeAdapter.fromDateTimeToString(rule.getStartTime());
		this.end_time = DateTimeAdapter.fromDateTimeToString(rule.getEndTime());
		this.last_updated = DateTimeAdapter.fromDateTimeToString(rule.getLastUpdated());
		this.last_executed = DateTimeAdapter.fromDateTimeToString(rule.getLastExecuted());
		this.last_triggered = DateTimeAdapter.fromDateTimeToString(rule.getLastTriggered());
		
		schedules = new ArrayList<OfflineSchedulerScheduleDTO>();
		List<Schedule> scheduleList = rule.getSchedules();
		if(scheduleList != null && scheduleList.size()>0){
			for(int i=0; i<scheduleList.size(); i++){
				Schedule schedule = scheduleList.get(i);
				if(schedule != null && schedule.getIsActive()){
					OfflineSchedulerScheduleDTO offlineSchedulerSchedule = new OfflineSchedulerScheduleDTO(schedule);
					schedules.add(offlineSchedulerSchedule);
				}
			}
		}
	}
	
}
