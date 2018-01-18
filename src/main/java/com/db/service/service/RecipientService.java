package com.db.service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.service.dao.RecipientDAO;
import com.db.service.dao.RecipientTypeDAO;
import com.db.service.dao.RuleDAO;
import com.db.service.dto.RecipientDTO;
import com.db.service.entity.Recipient;
import com.db.service.entity.RecipientType;
import com.db.service.entity.Rule;

@Component
public class RecipientService {
	@Autowired
	RecipientDAO recipientRepo;
	
	@Autowired
	RecipientTypeDAO recipientTypeRepo;
	
	@Autowired
	RuleDAO ruleRepo;
	
	
	private RecipientType getRecipientTypeByValue(String value) throws Exception{
		List<RecipientType> recipientTypes = recipientTypeRepo.findRecipientTypeFromValue(value);
		if(recipientTypes != null && recipientTypes.size()==1){
			return recipientTypes.get(0);
		}
		else{
			throw new Exception();
		}
	}
	
	public List<RecipientDTO> getRecipients(){
		List<RecipientDTO> resList = new ArrayList<RecipientDTO>();
		try{
			List<Recipient> recipients = recipientRepo.findAll();
			if(recipients != null && recipients.size()>0){
				for(int i=0; i<recipients.size(); i++){
					RecipientDTO recDTO = new RecipientDTO(recipients.get(i));
					resList.add(recDTO);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	public RecipientDTO getRecipientById(String recIdStr){
		RecipientDTO recDTO = null;
		try{
			int recId = Integer.parseInt(recIdStr);
			Recipient rec = recipientRepo.findOne(recId);
			if(rec != null){
				recDTO = new RecipientDTO(rec);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return recDTO;
	}
	
	public List<RecipientDTO> getRecipientsByRuleId(String ruleIdStr){
		List<RecipientDTO> resList = new ArrayList<RecipientDTO>();
		try{
			int ruleId = Integer.parseInt(ruleIdStr);
			List<Recipient> recipients = ruleRepo.findOne(ruleId).getRecipients();
			if(recipients != null && recipients.size()>0){
				for(int i=0; i<recipients.size(); i++){
					RecipientDTO recipientDTO = new RecipientDTO(recipients.get(i));
					resList.add(recipientDTO);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	public boolean saveRecipient(String ruleIdStr, RecipientDTO recipientDTO){
		boolean res = false;
		try{
			int ruleId = Integer.parseInt(ruleIdStr);
			String recipientTypeStr = recipientDTO.getRecipientType();
			RecipientType recipientType = getRecipientTypeByValue(recipientTypeStr);
			Rule rule = ruleRepo.findOne(ruleId);
			if(recipientType != null && rule != null){
				recipientRepo.save(recipientDTO.toRecipient(rule, recipientType));
				res = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public boolean updateRecipient(String ruleIdStr, String recipientIdStr, RecipientDTO recipientDTO){
		boolean res = false;
		try{
			int ruleId = Integer.parseInt(ruleIdStr);
			int id = Integer.parseInt(recipientIdStr);
			String target = recipientDTO.getTarget();
			boolean isActive = recipientDTO.getIsActive();
			Date lastUpdated =  new Date();
			String recipientTypeStr = recipientDTO.getRecipientType();
			RecipientType recipientType = getRecipientTypeByValue(recipientTypeStr);
			//TODO: need to verify this mechanism
			
			//recipientRepo.updateRecipientById(target, lastUpdated, recipientType, id);
			Recipient recipient = recipientRepo.findOne(id);
			if(recipient != null && recipient.getRule().getId() == ruleId && recipientType != null){
				recipient.setTarget(target);
				recipient.setIsActive(isActive);
				recipient.setLastUpdated(lastUpdated);
				recipient.setRecipientType(recipientType);
				recipientRepo.save(recipient);
				res=true;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
}
