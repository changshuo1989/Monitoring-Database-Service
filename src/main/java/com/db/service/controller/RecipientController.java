package com.db.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.service.dto.RecipientDTO;
import com.db.service.service.RecipientService;

@RestController
@RequestMapping("api")
public class RecipientController {
	@Autowired
	RecipientService recipientService;
	
	@RequestMapping(value="/recipients", method=RequestMethod.GET)
	public List<RecipientDTO> getRecipients(){
		return recipientService.getRecipients();		
	}
	
	@RequestMapping(value="/recipients/{recipient_id}", method=RequestMethod.GET)
	public RecipientDTO getRecipientById(@PathVariable("recipient_id") String recipientIdStr){
		return recipientService.getRecipientById(recipientIdStr);
	}
	
	@RequestMapping(value="/rules/{rule_id}/recipients", method=RequestMethod.GET)
	public List<RecipientDTO> getRecipientsByRuleId(@PathVariable("rule_id") String ruleIdStr){
		return recipientService.getRecipientsByRuleId(ruleIdStr);
	}
	
	@RequestMapping(value="/rules/{rule_id}/recipients", method=RequestMethod.POST)
	public boolean saveRecipient(@PathVariable("rule_id") String ruleIdStr, 
			@RequestBody(required=false)  RecipientDTO recipientDTO){
		return recipientService.saveRecipient(ruleIdStr, recipientDTO);
	}
	
	@RequestMapping(value="/rules/{rule_id}/recipients/{recipient_id}", method=RequestMethod.PUT)
	public boolean updateRecipient(@PathVariable("rule_id") String ruleIdStr, 
			@PathVariable("recipient_id") String recipientIdStr,
			@RequestBody(required=false)  RecipientDTO recipientDTO){
		return recipientService.updateRecipient(ruleIdStr, recipientIdStr, recipientDTO);
	}
	
	
	
	
	
}
