package com.db.service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.db.service.dao.ConnectionDAO;
import com.db.service.dao.RuleDAO;
import com.db.service.dao.RuleStatusDAO;
import com.db.service.dao.RuleTypeDAO;
import com.db.service.dao.ScheduleDAO;
import com.db.service.dto.CheckDTO;
import com.db.service.dto.ConnectionDTO;
import com.db.service.dto.ExecutionServiceRuleDTO;
import com.db.service.dto.OfflineSchedulerRuleDTO;
import com.db.service.dto.RecipientDTO;
import com.db.service.dto.RuleDTO;
import com.db.service.entity.Check;
import com.db.service.entity.Connection;
import com.db.service.entity.Recipient;
import com.db.service.entity.Rule;
import com.db.service.entity.RuleStatus;
import com.db.service.entity.RuleType;
import com.db.service.entity.Schedule;
import com.db.service.utility.AESCryptTool;
import com.db.service.utility.DateTimeAdapter;

@Component
public class RuleService {
	
	@Autowired
	RuleDAO ruleRepo;
	
	@Autowired
	RuleStatusDAO ruleStatusRepo;
	
	@Autowired
	RuleTypeDAO ruleTypeRepo;
	
	@Autowired
	ConnectionDAO connectionRepo;
	
	@Autowired
	ScheduleDAO scheduleRepo;
	
	
	
	private RuleType getRuleTypeByValue(String ruleTypeValue) throws Exception{
		List<RuleType> ruleTypes = ruleTypeRepo.findRuleTypeFromValue(ruleTypeValue);
		if(ruleTypes != null && ruleTypes.size()==1){
			return ruleTypes.get(0);
		}
		else{
			throw new Exception();
		}
	}

	
	private RuleStatus getRuleStatusByValue (String ruleStatusValue) throws Exception{
		List<RuleStatus> ruleStatusList = ruleStatusRepo.findRuleStatusFromValue(ruleStatusValue);
		if(ruleStatusList != null && ruleStatusList.size() == 1){
			return ruleStatusList.get(0);
		}
		else {
			throw new Exception();
		}
	}
	
	public List<RuleDTO> getRules(){
		List<RuleDTO> resList = new ArrayList<RuleDTO>();
		try{
			List<Rule> rules = ruleRepo.findAll();
			if(rules != null && rules.size() > 0){
				for (Rule rule : rules) {
					resList.add(new RuleDTO(rule));
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	
	public RuleDTO getRuleById(String ruleIdStr){
		RuleDTO ruleDTO = null;
		try{
			int ruleId = Integer.parseInt(ruleIdStr);
			Rule rule = ruleRepo.findOne(ruleId);
			if(rule != null){
				ruleDTO = new RuleDTO(rule);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ruleDTO;
	}
	
	public List<RuleDTO> getRulesByConnectionId(String connIdStr){
		List<RuleDTO> resList = new ArrayList<>();
		try{
			int connId = Integer.parseInt(connIdStr);
			Connection conn = connectionRepo.findOne(connId);
			if(conn != null){
				List<Rule> rules = conn.getRules();
				if(rules!=null && rules.size()>0){
					for (Rule rule : rules) {
						resList.add(new RuleDTO(rule));
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	public List<OfflineSchedulerRuleDTO> getRulesByStatusForOfflineScheduler(String statusValue){
		List<OfflineSchedulerRuleDTO> resList = new ArrayList<>();
		try{
			RuleStatus ruleStatus = getRuleStatusByValue(statusValue);
			List<Rule> rules = ruleRepo.findRuleByStatus(ruleStatus);
			if(rules != null && rules.size()>0){
				for(int i=0; i<rules.size(); i++){
					OfflineSchedulerRuleDTO rule = new OfflineSchedulerRuleDTO(rules.get(i));
					resList.add(rule);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return resList;
	}
	
	public List<ExecutionServiceRuleDTO> getTriggeredRulesForExecutionService(List<Integer> ids){
		List<ExecutionServiceRuleDTO> resList = new ArrayList<>();
		try{
			if (ids != null && ids.size() > 0){
				//dedupe
				ids = new ArrayList<>(new HashSet<>(ids));
				for (int i=0; i<ids.size(); i++){
					int ruleId = ids.get(i);
					if(ruleRepo.exists(ruleId)){
						Rule rule = ruleRepo.getOne(ruleId);
					
						Connection conn = rule.getConnection();
						List<Check> checks = rule.getChecks();
						List<Recipient> recipients = rule.getRecipients();
						
						RuleDTO ruleDto = new RuleDTO(rule);
						ConnectionDTO connDto = new ConnectionDTO(conn);
						
						List<CheckDTO> checkDtos = new ArrayList<>();
						List<RecipientDTO> recipientDtos = new ArrayList<>();
						
						if(checks != null && checks.size() > 0){
							for(int j=0; j<checks.size(); j++){
								CheckDTO checkDto = new CheckDTO(checks.get(j));
								checkDtos.add(checkDto);
							}
						}
						if(recipients != null && recipients.size() > 0){
							for (int j =0; j<recipients.size(); j++){
								RecipientDTO recipientDto = new RecipientDTO(recipients.get(j));
								recipientDtos.add(recipientDto);
							}
						}
						ExecutionServiceRuleDTO executionServiceRuleDTO = new ExecutionServiceRuleDTO(ruleDto, connDto, checkDtos, recipientDtos);
						resList.add(executionServiceRuleDTO);
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	
	public boolean saveRule(String connIdStr, RuleDTO ruleDTO){
		boolean res = false;
		try{
			int connId = Integer.parseInt(connIdStr);
			String ruleTypeStr = ruleDTO.getRuleType();
			String ruleStatusStr = ruleDTO.getRuleStatus();
			
			Connection connection = connectionRepo.findOne(connId);
			RuleType ruleType = getRuleTypeByValue(ruleTypeStr);
			RuleStatus ruleStatus = getRuleStatusByValue(ruleStatusStr);
			
			if(connection != null && ruleType != null && ruleStatus != null){
				ruleRepo.save(ruleDTO.toRule(connection, ruleStatus, ruleType));
				res=true;
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean updateRule(String connIdStr, String ruleIdStr, RuleDTO ruleDTO){
		boolean res = false;
		try{
			int connId = Integer.parseInt(connIdStr);
			Connection conn = connectionRepo.findOne(connId);
			if(conn != null){		
				String salt = conn.getUser().getSalt();
				int id = Integer.parseInt(ruleIdStr);
				
				String name = ruleDTO.getName();
				String encrtContent = AESCryptTool.encrypt(ruleDTO.getContent(),salt);
				
				String ruleTypeStr = ruleDTO.getRuleType();
				String ruleStatusStr = ruleDTO.getRuleStatus();
				
				RuleType ruleType = getRuleTypeByValue(ruleTypeStr);
				RuleStatus ruleStatus = getRuleStatusByValue(ruleStatusStr);
				
				Date startTime = DateTimeAdapter.fromStringToDateTime(ruleDTO.getStartTime());
				Date endTime = DateTimeAdapter.fromStringToDateTime(ruleDTO.getEndTime());
				
				Date lastUdapted = new Date();
				
				boolean notifyWhenEmptyData = ruleDTO.getNotifyWhenEmptyData();
				
				Rule rule = ruleRepo.findOne(id);
				if(rule != null && rule.getConnection().getId() == connId && ruleType != null && ruleStatus != null){
					rule.setName(name);
					rule.setContent(encrtContent);
					rule.setRuleType(ruleType);
					rule.setRuleStatus(ruleStatus);
					rule.setStartTime(startTime);
					rule.setEndTime(endTime);
					rule.setLastUpdated(lastUdapted);
					rule.setNotifyWhenEmptyData(notifyWhenEmptyData);
	
					ruleRepo.save(rule);
					res=true;
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public boolean updateTriggeredRulesAndSchedules(Map<Integer, List<Integer>> triggeredRulesAndSchedules){
		
		boolean res = false;
		try{
			if(triggeredRulesAndSchedules != null && triggeredRulesAndSchedules.size()>0){
				for(Integer ruleId : triggeredRulesAndSchedules.keySet()){
					List<Integer> triggeredSchedules = triggeredRulesAndSchedules.get(ruleId);
					if(triggeredSchedules != null && triggeredSchedules.size() > 0){
						Date now = new Date();
						//update rule for last_triggered
						Rule rule = ruleRepo.findOne(ruleId);
						rule.setLastTriggered(now);
						ruleRepo.save(rule);
						//update schedule for last_triggered
						for(int i=0; i<triggeredSchedules.size(); i++){
							Schedule schedule = scheduleRepo.findOne(triggeredSchedules.get(i));
							schedule.setLastTriggered(now);
							scheduleRepo.save(schedule);
						}
					}
				}
			}
			res = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	

}
