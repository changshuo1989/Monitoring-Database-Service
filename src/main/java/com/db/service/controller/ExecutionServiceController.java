package com.db.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.service.dto.ExecutionServiceRuleDTO;
import com.db.service.service.RuleService;

@RestController
@RequestMapping("monitoring-database-service/api")
public class ExecutionServiceController {
	
	@Autowired
	RuleService ruleService;
	
	@RequestMapping(value = "/execution-service/rules/triggered/{ids}", method=RequestMethod.GET)
	public List<ExecutionServiceRuleDTO> getTriggeredRulesForExecutionService(@PathVariable("ids") List<Integer> ids){
		return ruleService.getTriggeredRulesForExecutionService(ids);
	}
	
}
