package com.tec.pattern.principle.lkp;

import java.util.ArrayList;
import java.util.List;

public class OldCompanyManager {
	public List<Employee> getAllEmployee(){
		List<Employee> list = new ArrayList<Employee>();
		for(int i=0; i<30; i++){
			Employee emp = new Employee();
			//为总公司人员按顺序分配一个ID
			emp.setId("总公司"+i);
			list.add(emp);
		}
		return list;
	}
	
	public void printAllEmployee(OldSubCompanyManager sub){
		List<SubEmployee> list1 = sub.getAllEmployee();
		for(SubEmployee e:list1){
			System.out.println(e.getId());
		}

		List<Employee> list2 = this.getAllEmployee();
		for(Employee e:list2){
			System.out.println(e.getId());
		}
	}
}
