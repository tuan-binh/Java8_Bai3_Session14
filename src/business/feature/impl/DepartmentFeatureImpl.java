package business.feature.impl;

import business.entity.Department;
import business.feature.IDepartmentFeature;
import business.utils.IOFile;
import business.utils.PathReferences;

import java.util.ArrayList;
import java.util.List;

public class DepartmentFeatureImpl implements IDepartmentFeature
{
	public static IOFile<Department> ioFileDepartment = new IOFile<>();
	public static List<Department> departments = ioFileDepartment.readFromFile(PathReferences.PATH_DEPARTMENT);
	
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
		// về sau mọi người làm việc với file thì cho nay sẽ được lưu vào file
		ioFileDepartment.writeToFile(departments, PathReferences.PATH_DEPARTMENT);
	}
	
	@Override
	public void delete(String id)
	{
		departments.remove(findIndexById(id));
		ioFileDepartment.writeToFile(departments, PathReferences.PATH_DEPARTMENT);
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
