package com.db.service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.db.service.dao.ConnectionDAO;
import com.db.service.dao.ConnectionTypeDAO;
import com.db.service.dao.UserDAO;
import com.db.service.dto.ConnectionDTO;
import com.db.service.entity.Connection;
import com.db.service.entity.ConnectionType;
import com.db.service.entity.User;
import com.db.service.utility.AESCryptTool;

@Component
public class ConnectionService {
	@Autowired
	ConnectionDAO connectionRepo;
	
	@Autowired
	UserDAO userRepo;
	
	@Autowired
	ConnectionTypeDAO connectionTypeRepo;
	
	
	private ConnectionType getConnectionTypeByValue(String value) throws Exception{
		List<ConnectionType> connectionTypes = connectionTypeRepo.findConnectionTypeFromValue(value);
		if(connectionTypes != null && connectionTypes.size()==1){
			return connectionTypes.get(0);
		}
		else{
			throw new Exception();
		}
	}
	
	
	public List<ConnectionDTO> getConnections(){
		List<ConnectionDTO> resList = new ArrayList<ConnectionDTO>();
		try{
			List<Connection> connections = connectionRepo.findAll();
			if(connections != null && connections.size() > 0){
				for(int i=0; i< connections.size(); i++){
					ConnectionDTO connDTO = new ConnectionDTO(connections.get(i));
					resList.add(connDTO);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	public ConnectionDTO getConnectionById(String connIdStr){
		ConnectionDTO connDTO = null;
		try{
			int connId = Integer.parseInt(connIdStr);
			Connection conn = connectionRepo.findOne(connId);
			if(conn != null){
				connDTO = new ConnectionDTO(conn);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return connDTO;
	}
	
	public List<ConnectionDTO> getConnectionsByUserId(String userIdStr){
		List<ConnectionDTO> resList = new ArrayList<ConnectionDTO>();
		try{
			int userId = Integer.parseInt(userIdStr);
			User user = userRepo.findOne(userId);
			if(user != null)
			{
				List<Connection> connections = user.getConnections();
				if (connections != null && connections.size()>0){
					for(int i = 0; i< connections.size(); i++){
						resList.add(new ConnectionDTO(connections.get(i)));
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}
	
	public boolean saveConnection(String userIdStr, ConnectionDTO connectionDTO){
		boolean res = false;
		try{
			int userId = Integer.parseInt(userIdStr);
			String connectionTypeStr = connectionDTO.getConnectionType();
			ConnectionType connectionType = getConnectionTypeByValue(connectionTypeStr);
			User user = userRepo.findOne(userId);
			if(connectionType != null && user != null){
				connectionRepo.save(connectionDTO.toConnection(user, connectionType));
				res = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	public boolean updateConnection(String userIdStr, String connectionIdStr, ConnectionDTO connectionDTO) {
		boolean res = false;
		try {
			int userId = Integer.parseInt(userIdStr);
			User user = userRepo.findOne(userId);
			if (user != null) {
				String salt = user.getSalt();
				int id = Integer.parseInt(connectionIdStr);

				String name = connectionDTO.getName();
				String host = AESCryptTool.encrypt(connectionDTO.getHost(), salt);
				String port = AESCryptTool.encrypt(String.valueOf(connectionDTO.getPort()), salt);
				String db = AESCryptTool.encrypt(connectionDTO.getDb(), salt);
				String username = AESCryptTool.encrypt(connectionDTO.getUsername(), salt);
				String password = AESCryptTool.encrypt(connectionDTO.getPassword(), salt);
				boolean isActive = connectionDTO.getIsActive();
				Date lastUpdated = new Date();
				Connection conn = connectionRepo.findOne(id);
				if(conn != null && conn.getUser().getId() == userId){
					connectionRepo.updateConnectionById(name, host, port, db, username, password, isActive, lastUpdated, id);
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
}
