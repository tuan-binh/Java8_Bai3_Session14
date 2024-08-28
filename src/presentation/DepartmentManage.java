package presentation;

import business.entity.Department;
import business.entity.Employee;
import business.feature.IDepartmentFeature;
import business.feature.impl.DepartmentFeatureImpl;
import business.feature.impl.EmployeeFeatureImpl;

import java.util.List;
import java.util.Scanner;

public class DepartmentManage
{
	IDepartmentFeature departmentFeature = new DepartmentFeatureImpl();
	
	public void menuDepartment(Scanner sc)
	{
		boolean isLoop = true;
		do
		{
			System.out.println("""
											======================= MENU =======================
														1. Hiển thị danh sách phòng ban
														2. Thêm mới phòng ban
														3. Sửa tên phòng ban
														4. Hiển thị nhân viên theo mã phòng ban
														5. Xóa phòng ban
														6. Quay lại
											====================================================
											Lựa chọn đê:
							""");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice)
			{
				case 1:
				{
					showDepartment();
					break;
				}
				case 2:
				{
					addNewDepartment(sc);
					break;
				}
				case 3:
				{
					editDepartmentName(sc);
					break;
				}
				case 4:
				{
					showEmployeesByDepId(sc);
					break;
				}
				case 5:
				{
					deleteDep(sc);
					break;
				}
				case 6:
				{
					isLoop = false;
					break;
				}
				default:
					System.err.println("Nhập lại từ 1 -> 6 đê");
			}
		}
		while (isLoop);
	}
	
	private void deleteDep(Scanner sc)
	{
		System.out.println("Nhập vào mã phòng ban: ");
		String depId = sc.nextLine();
		
		boolean isExist = DepartmentFeatureImpl.departments.stream()
				  .anyMatch(item -> item.getDepartmentId().equals(depId));
		if (!isExist)
		{
			System.err.println("Không tồn tại mã phòng ban = " + depId);
			return;
		}
		
		// kiểm tra xem phòng ban đang xóa có nhân viên nào không
		boolean hasEmp = EmployeeFeatureImpl.employees.stream()
				  .anyMatch(item -> item.getDepartment().getDepartmentId().equals(depId));
		if (hasEmp)
		{
			System.err.println("Phòng ban không thể xóa");
		}
		else
		{
			departmentFeature.delete(depId);
		}
	}
	
	private void showEmployeesByDepId(Scanner sc)
	{
		System.out.println("Nhập mã phòng ban: ");
		String depId = sc.nextLine();
		List<Employee> employees = EmployeeFeatureImpl.employees.stream()
				  .filter(item -> item.getDepartment().getDepartmentId().equals(depId))
				  .toList();
		if (employees.isEmpty())
		{
			System.err.println("Không tìm thấy nhân viên nào");
			return;
		}
		employees.forEach(Employee::displayData);
	}
	
	private void editDepartmentName(Scanner sc)
	{
		System.out.println("Nhập vào mã phòng ban: ");
		String depId = sc.nextLine();
		int indexUpdate = departmentFeature.findIndexById(depId);
		if (indexUpdate == -1)
		{
			System.err.println("Không tồn tại mã phòng ban = " + depId);
		}
		else
		{
			Department oldDep = DepartmentFeatureImpl.departments.get(indexUpdate);
			oldDep.setDepartmentName(oldDep.inputDepNameUpdate(sc));
			// mục đích về sau sẽ cap nhat du lieu vao file
			departmentFeature.addOrUpdate(oldDep);
		}
	}
	
	private void addNewDepartment(Scanner sc)
	{
		System.out.println("Bạn muốn thêm bao nhiêu phòng ban: ");
		int number = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < number; i++)
		{
			Department department = new Department();
			department.inputData(sc);
			departmentFeature.addOrUpdate(department);
		}
	}
	
	private void showDepartment()
	{
		if (DepartmentFeatureImpl.departments.isEmpty())
		{
			System.err.println("Danh sách trống...");
			return;
		}
		DepartmentFeatureImpl.departments.forEach(Department::displayData);
	}
	
	
}
