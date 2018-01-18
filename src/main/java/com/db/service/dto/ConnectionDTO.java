package com.db.service.dto;

import java.util.Date;

import com.db.service.entity.Connection;
import com.db.service.entity.ConnectionType;
import com.db.service.entity.User;
import com.db.service.utility.AESCryptTool;
import com.db.service.utility.DateTimeAdapter;

public class ConnectionDTO {
	
	private int id;
	private int userId;
	private String name;
	private String connectionType;
	private String host;
	private int port;
	private String db;
	private String username;
	private String password;
	private boolean isActive;
	private String lastUpdated;

	
	public ConnectionDTO() {
		
	}
	
	public ConnectionDTO(Connection conn) throws Exception{
		
		this.id=conn.getId();
		this.userId=conn.getUser().getId();
		this.name=conn.getName();
		this.connectionType=conn.getConnectionType().getValue();
		
		String salt = conn.getUser().getSalt();
		this.host=AESCryptTool.decrypt(conn.getHost(), salt);
		this.port=Integer.parseInt(AESCryptTool.decrypt(conn.getPort(), salt));
		this.db=AESCryptTool.decrypt(conn.getDb(), salt);
		this.username=AESCryptTool.decrypt(conn.getUsername(), salt);
		this.password=AESCryptTool.decrypt(conn.getPassword(), salt);
		this.isActive=conn.getIsActive();
		this.lastUpdated=DateTimeAdapter.fromDateTimeToString(conn.getLastUpdated());
	}
	
	public Connection toConnection (User user, ConnectionType ct) throws Exception{
		Connection connection = new Connection();
		connection.setName(this.getName());
		String salt = user.getSalt();
		connection.setHost(AESCryptTool.encrypt(this.getHost(), salt));
		connection.setPort(AESCryptTool.encrypt(String.valueOf(this.getPort()), salt));
		connection.setDb(AESCryptTool.encrypt(this.getDb(), salt));
		connection.setUsername(AESCryptTool.encrypt(this.getUsername(),salt));
		connection.setPassword(AESCryptTool.encrypt(this.getPassword(), salt));
		connection.setIsActive(this.isActive);
		connection.setLastUpdated(new Date());
		connection.setUser(user);
		connection.setConnectionType(ct);

		return connection;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getDb() {
		return db;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
