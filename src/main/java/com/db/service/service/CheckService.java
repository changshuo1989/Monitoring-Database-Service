package com.db.service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.service.dao.CheckBenchmarkTypeDAO;
import com.db.service.dao.CheckConjunctionTypeDAO;
import com.db.service.dao.CheckDAO;
import com.db.service.dao.CheckLogicTypeDAO;
import com.db.service.dao.CheckOperatorTypeDAO;
import com.db.service.dao.RuleDAO;
import com.db.service.dto.CheckDTO;
import com.db.service.entity.Check;
import com.db.service.entity.CheckBenchmarkType;
import com.db.service.entity.CheckConjunctionType;
import com.db.service.entity.CheckLogicType;
import com.db.service.entity.CheckOperatorType;
import com.db.service.entity.Rule;


@Component
public class CheckService {
	@Autowired
	CheckDAO checkRepo;
	
	@Autowired
	CheckBenchmarkTypeDAO checkBenchmarkTypeRepo;
	
	@Autowired
	CheckConjunctionTypeDAO checkConjunctionTypeRepo;
	
	@Autowired
	CheckLogicTypeDAO  checkLogicTypeRepo;
	
	@Autowired
	CheckOperatorTypeDAO checkOperatorTypeRepo;
	
	@Autowired
	RuleDAO ruleRepo;
	
	
	private CheckBenchmarkType getCheckBenchmarkTypeByValue(String value) throws Exception{
		List<CheckBenchmarkType> checkBenchmarkTypes = checkBenchmarkTypeRepo.findCheckBenchmarkTypeFromValue(value);
		if(checkBenchmarkTypes != null && checkBenchmarkTypes.size()==1){
			return checkBenchmarkTypes.get(0);
		}
		else{
			throw new Exception();
		}
		
	}
	
	private CheckConjunctionType getCheckConjunctionTypeByValue(String value) throws Exception{
		List<CheckConjunctionType> checkConjunctionTypes = checkConjunctionTypeRepo.findCheckConjunctionTypeFromValue(value);
		if(checkConjunctionTypes != null && checkConjunctionTypes.size()==1){
			return checkConjunctionTypes.get(0);
		}
		else{
			throw new Exception();
		}
	}
	
	private CheckLogicType getCheckLogicTypeByValue(String value) throws Exception{
		List<CheckLogicType> checkLogicTypes = checkLogicTypeRepo.findCheckLogicTypeFromValue(value);
		if(checkLogicTypes != null && checkLogicTypes.size()==1){
			return checkLogicTypes.get(0);
		}
		else{
			throw new Exception();
		}
	}
	
	private CheckOperatorType getCheckOperatorTypeByValue(String value) throws Exception{
		List<CheckOperatorType> checkOperatorTypes = checkOperatorTypeRepo.findCheckOperatorTypeFromValue(value);
		if(checkOperatorTypes != null && checkOperatorTypes.size() == 1){
			return checkOperatorTypes.get(0);
		}
		else{
			throw new Exception();
		}
	}
	
	
	public List<CheckDTO> getChecks(){
		List<CheckDTO> resList = new ArrayList<>();
		try{
			List<Check> checks = checkRepo.findAll();
			if(checks != null && checks.size()>0){
				for(int i=0; i<checks.size(); i++){
					CheckDTO checkDTO = new CheckDTO(checks.get(i));
					resList.add(checkDTO);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	public CheckDTO getCheckById(String checkIdStr){
		CheckDTO checkDTO = null;
		try{
			int checkId = Integer.parseInt(checkIdStr);
			Check check = checkRepo.findOne(checkId);
			if(check != null){
				checkDTO = new CheckDTO(check);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return checkDTO;
	}
	
	public List<CheckDTO> getChecksByRuleId(String ruleIdStr){
		List<CheckDTO> resList = new ArrayList<>();
		try{
			int ruleId = Integer.parseInt(ruleIdStr);
			Rule rule = ruleRepo.findOne(ruleId);
			if(rule != null){
				List<Check> checks =  rule.getChecks();
				if(checks != null && checks.size()>0){
					for(int i=0; i<checks.size(); i++){
						CheckDTO checkDTO = new CheckDTO(checks.get(i));
						resList.add(checkDTO);
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	public boolean saveCheck(String ruleIdStr, CheckDTO checkDTO){
		boolean res = false;
		try{
			int ruleId = Integer.parseInt(ruleIdStr);
			String checkBenchmarkTypeStr = checkDTO.getCheckBenchmarkType();
			String checkConjunctionTypStr = checkDTO.getCheckConjunctionType();
			String checkLogicTypeStr = checkDTO.getCheckLogicType();
			String checkOperatorTypeStr = checkDTO.getCheckOperatorType();
			
			Rule rule = ruleRepo.findOne(ruleId);
			CheckBenchmarkType checkBenchmarkType = getCheckBenchmarkTypeByValue(checkBenchmarkTypeStr);
			CheckConjunctionType checkConjunctionType = getCheckConjunctionTypeByValue(checkConjunctionTypStr);
			CheckLogicType checkLogicType = getCheckLogicTypeByValue(checkLogicTypeStr);
			CheckOperatorType checkOperatorType = getCheckOperatorTypeByValue(checkOperatorTypeStr);
			
			if(rule != null && checkBenchmarkType != null 
					&& checkConjunctionType != null && checkLogicType != null
					&& checkOperatorType != null){
				checkRepo.save(checkDTO.toCheck(rule, checkBenchmarkType, checkConjunctionType, checkLogicType, checkOperatorType));
				res = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean updateCheck (String ruleIdStr, String checkIdStr, CheckDTO checkDTO){
		boolean res = false;
		try{
			int ruleId = Integer.parseInt(ruleIdStr);
			int id = Integer.parseInt(checkIdStr);
			int sequence = checkDTO.getSequence();
			String benchmark =  checkDTO.getBenchmark();
			
			String checkBenchmarkTypeStr = checkDTO.getCheckBenchmarkType();
			String checkConjunctionTypStr = checkDTO.getCheckConjunctionType();
			String checkLogicTypeStr = checkDTO.getCheckLogicType();
			String checkOperatorTypeStr = checkDTO.getCheckOperatorType();
			
			CheckBenchmarkType checkBenchmarkType = getCheckBenchmarkTypeByValue(checkBenchmarkTypeStr);
			CheckConjunctionType checkConjunctionType = getCheckConjunctionTypeByValue(checkConjunctionTypStr);
			CheckLogicType checkLogicType = getCheckLogicTypeByValue(checkLogicTypeStr);
			CheckOperatorType checkOperatorType = getCheckOperatorTypeByValue(checkOperatorTypeStr);
			
			String attributeName = checkDTO.getAttributeName();
			boolean isActive = checkDTO.getIsActive();
			Date lastUpdated =  new Date();
			
			Check check = checkRepo.findOne(id);
			if(check != null && check.getRule().getId() == ruleId && checkBenchmarkType != null 
					&& checkConjunctionType != null && checkLogicType != null
					&& checkOperatorType != null){
				check.setSequence(sequence);
				check.setBenchmark(benchmark);
				check.setCheckBenchmarkType(checkBenchmarkType);
				check.setCheckConjunctionType(checkConjunctionType);
				check.setCheckLogicType(checkLogicType);
				check.setCheckOperatorType(checkOperatorType);
				check.setAttributeName(attributeName);
				check.setIsActive(isActive);
				check.setLastUpdated(lastUpdated);
				checkRepo.save(check);
				res = true;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
}
