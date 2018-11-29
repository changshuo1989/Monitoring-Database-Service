package com.db.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.service.dto.OfflineSchedulerRuleDTO;
import com.db.service.service.RuleService;

@RestController
@RequestMapping("monitoring-database-service/api")
public class OfflineSchedulerController {
	@Autowired
	RuleService ruleService;
	

	@RequestMapping(value="/offline-scheduler/rules/{rule_status}", method=RequestMethod.GET)
	public List<OfflineSchedulerRuleDTO> getRuleBasedOnStatusForOffflineScheduler(@PathVariable("rule_status") String ruleStatus){
		return ruleService.getRulesByStatusForOfflineScheduler(ruleStatus);
	}
	
	@RequestMapping(value="/offline-scheduler/rules/triggered", method=RequestMethod.POST)
	public boolean updateTriggeredRulesAndSchedules(@RequestBody(required=false) Map<Integer, List<Integer>> triggeredRulesSchedules){
		return ruleService.updateTriggeredRulesAndSchedules(triggeredRulesSchedules);
	}
}
