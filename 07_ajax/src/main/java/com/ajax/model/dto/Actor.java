package com.ajax.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Actor {
	private String name;
	private String phone;
	private String img;
	private String profile;
	
	@Override
	public String toString() {
		return name+","+phone+","+profile;
	}
}
