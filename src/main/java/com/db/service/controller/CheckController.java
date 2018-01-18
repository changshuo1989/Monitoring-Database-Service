package com.db.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.service.dto.CheckDTO;
import com.db.service.service.CheckService;

@RestController
@RequestMapping("api")
public class CheckController {
	@Autowired
	CheckService checkService;
	
	@RequestMapping(value="/checks", method=RequestMethod.GET)
	public List<CheckDTO> getChecks(){
		return checkService.getChecks();
	}
	
	@RequestMapping(value="/checks/{check_id}", method=RequestMethod.GET)
	public CheckDTO getCheckById(@PathVariable("check_id") String checkIdStr){
		return checkService.getCheckById(checkIdStr);
	}
	
	@RequestMapping(value="/rules/{rule_id}/checks", method=RequestMethod.GET)
	public List<CheckDTO> getChecksByRuleId(@PathVariable("rule_id") String ruleIdStr){
		return checkService.getChecksByRuleId(ruleIdStr);
	}
	
	@RequestMapping(value="/rules/{rule_id}/checks", method=RequestMethod.POST)
	public boolean saveCheck(@PathVariable("rule_id") String ruleIdStr, 
			@RequestBody(required=false) CheckDTO checkDTO){
		return checkService.saveCheck(ruleIdStr, checkDTO);
	}
	
	@RequestMapping(value="/rules/{rule_id}/checks/{check_id}", method=RequestMethod.PUT)
	public boolean updateChcek(@PathVariable("rule_id") String ruleIdStr, 
			@PathVariable("check_id") String checkIdStr,
			@RequestBody(required=false) CheckDTO checkDTO){
		return checkService.updateCheck(ruleIdStr, checkIdStr, checkDTO);
	}
	
	
	

}
