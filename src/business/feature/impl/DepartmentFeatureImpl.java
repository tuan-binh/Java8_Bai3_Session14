package business.feature.impl;

import business.entity.Department;
import business.entity.Employee;
import business.feature.IDepartmentFeature;

import java.util.ArrayList;
import java.util.List;

public class DepartmentFeatureImpl implements IDepartmentFeature
{
	public static List<Department> departments = new ArrayList<>();
	
	@Override
	public void addOrUpdate(Department department)
	{
		// tìm vị trí id xem có tồn tại trong mảng hay không
		int indexCheck = findIndexById(department.getDepartmentId());
		if (indexCheck == -1)
		{
			// chức năng thêm mới
			departments.add(department);
		}
		else
		{
			// chức năng cập nhật
			departments.set(indexCheck, department);
		}
	}
	
	@Override
	public void delete(String id)
	{
		departments.remove(findIndexById(id));
	}
	
	@Override
	public int findIndexById(String id)
	{
		return departments.stream()
				  // chuyển đôi thành mảng id
				  .map(Department::getDepartmentId)
				  // chuyển từ stream -> List
				  .toList()
				  .indexOf(id);
	}
}
