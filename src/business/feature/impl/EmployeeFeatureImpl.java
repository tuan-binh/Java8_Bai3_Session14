package business.feature.impl;

import business.entity.Employee;
import business.feature.IEmployeeFeature;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFeatureImpl implements IEmployeeFeature
{
	public static List<Employee> employees = new ArrayList<>();
	
	@Override
	public void addOrUpdate(Employee employee)
	{
		int indexCheck = findIndexById(employee.getEmployeeId());
		if (indexCheck == -1)
		{
			// chức năng thêm mới
			employees.add(employee);
		}
		else
		{
			// chức năng cập nhật
			employees.set(indexCheck, employee);
		}
	}
	
	@Override
	public void delete(String id)
	{
		employees.remove(findIndexById(id));
	}
	
	@Override
	public int findIndexById(String id)
	{
		return employees.stream()
				  .map(Employee::getEmployeeId)
				  .toList()
				  .indexOf(id);
	}
}
