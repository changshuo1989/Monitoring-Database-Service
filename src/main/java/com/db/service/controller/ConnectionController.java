package com.db.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.service.dto.ConnectionDTO;
import com.db.service.service.ConnectionService;

@RestController
@RequestMapping("api")
public class ConnectionController {
	@Autowired
	ConnectionService connectionService;
	
	@CrossOrigin
	@RequestMapping(value="/connections", method=RequestMethod.GET)
	public List<ConnectionDTO> getConnections(){
		return connectionService.getConnections();
	}
	
	@CrossOrigin
	@RequestMapping(value="/connections/{connection_id}", method=RequestMethod.GET)
	public ConnectionDTO getConnectionById(@PathVariable("connection_id") String connectionIdStr){
		return connectionService.getConnectionById(connectionIdStr);
	}
	
	@CrossOrigin
	@RequestMapping(value="/users/{user_id}/connections", method=RequestMethod.GET)
	public List<ConnectionDTO> getConnectionsByUserId(@PathVariable("user_id") String userIdStr){
		return connectionService.getConnectionsByUserId(userIdStr);	
	}
	
	@CrossOrigin
	@RequestMapping(value="/users/{user_id}/connections", method=RequestMethod.POST)
	public boolean saveConnection(@PathVariable("user_id") String userIdStr, 
			@RequestBody(required=false) ConnectionDTO connDTO){
		return connectionService.saveConnection(userIdStr, connDTO);
	}
	
	@CrossOrigin
	@RequestMapping(value="/users/{user_id}/connections/{connection_id}", method=RequestMethod.PUT)
	public boolean updateConnection(@PathVariable("user_id") String userIdStr, 
			@PathVariable("connection_id") String connectionIdStr,
			@RequestBody(required=false) ConnectionDTO connDTO){
		return connectionService.updateConnection(userIdStr, connectionIdStr, connDTO);
	}
	
	
	
	
	

}
