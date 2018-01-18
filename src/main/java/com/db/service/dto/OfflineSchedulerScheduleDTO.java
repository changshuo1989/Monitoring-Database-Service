package com.db.service.dto;

import com.db.service.entity.Schedule;
import com.db.service.utility.DateTimeAdapter;

public class OfflineSchedulerScheduleDTO {
	public int id;
	public String type;
	public int date;
	public int hour_of_day;
	public int repeat_every;
	public String last_updated;
	public String last_triggered;
	
	
	public OfflineSchedulerScheduleDTO (Schedule schedule){
		this.id = schedule.getId();
		this.type = schedule.getScheduleType().getValue();
		this.date = schedule.getDate();
		this.hour_of_day = schedule.getHourOfDay();
		this.repeat_every =schedule.getRepeatEvery();
		this.last_updated = DateTimeAdapter.fromDateTimeToString(schedule.getLastUpdated());
		this.last_triggered = DateTimeAdapter.fromDateTimeToString(schedule.getLastTriggered());
	}
}
