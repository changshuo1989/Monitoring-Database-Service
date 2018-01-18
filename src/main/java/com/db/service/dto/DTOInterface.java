package com.db.service.dto;


public interface DTOInterface<T, E>{
	
	public T toEntity() throws Exception;

}
