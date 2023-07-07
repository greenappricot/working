package com.mybatis.dy.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude= {"employees"}) // toString 할 때 employees는 제외할 수 있다.
public class Department {
	private String deptId;
	private String deptTitle;
	private String locationId;
	
	private List<Employee> employees;
	// department: employee는 1:many 관계로 하나의 부서는 다수의 직원을 가질 수 있다.  
}
