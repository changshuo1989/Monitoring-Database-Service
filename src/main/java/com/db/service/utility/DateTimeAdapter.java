package com.db.service.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeAdapter {
	public static final DateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	
	
	public static String fromDateTimeToString(Date dateTime){
		String dateTimeStr = "";
		if (dateTime != null){
			dateTimeStr=dateTimeFormat.format(dateTime);
		}
		return dateTimeStr;
	}
	
	public static Date fromStringToDateTime (String dateTimeStr){
		Date dateTime=null;
		try{
			if(dateTimeStr != null){
				dateTime=dateTimeFormat.parse(dateTimeStr);
			}
		}
		catch(ParseException e){
			
			//TODO: write into logger
		}
		return dateTime;
		
	}
}
