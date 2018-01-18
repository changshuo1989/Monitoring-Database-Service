package com.db.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.service.dto.RuleDTO;
import com.db.service.service.RuleService;

@RestController
@RequestMapping("api")
public class RuleController {
	@Autowired
	RuleService ruleService;
	
	@RequestMapping(value="/rules", method=RequestMethod.GET)
	public List<RuleDTO> getRules(){
		return ruleService.getRules();
	}
	
	@RequestMapping(value="/rules/{rule_id}", method=RequestMethod.GET)
	public RuleDTO getRuleById(@PathVariable("rule_id") String ruleIdStr){
		return ruleService.getRuleById(ruleIdStr);
	}
	
	@RequestMapping(value="/connections/{connection_id}/rules", method=RequestMethod.GET)
	public List<RuleDTO> getRulesByConnectionId(@PathVariable("connection_id") String ConnectionIdStr){
		return ruleService.getRulesByConnectionId(ConnectionIdStr);
	}
	
	@RequestMapping(value="/connections/{connection_id}/rules", method=RequestMethod.POST)
	public boolean saveRule(@PathVariable("connection_id") String ConnectionIdStr, 
			@RequestBody(required=false)  RuleDTO ruleDTO){
		return ruleService.saveRule(ConnectionIdStr, ruleDTO);
	}
	
	
	@RequestMapping(value="/connections/{connection_id}/rules/{rule_id}", method=RequestMethod.PUT)
	public boolean updateRule(@PathVariable("connection_id") String ConnectionIdStr, 
			@PathVariable("rule_id") String ruleIdStr,
			@RequestBody(required=false)  RuleDTO ruleDTO){
		return ruleService.updateRule(ConnectionIdStr, ruleIdStr, ruleDTO);
	}
	
	
	
}
