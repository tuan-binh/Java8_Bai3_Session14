package business.entity;

import business.feature.impl.DepartmentFeatureImpl;

import java.util.Scanner;

public class Department
{
	private String departmentId;
	private String departmentName;
	private int totalMembers;
	
	public Department()
	{
	}
	
	public Department(int totalMembers, String departmentName, String departmentId)
	{
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.totalMembers = totalMembers;
	}
	
	public String getDepartmentId()
	{
		return departmentId;
	}
	
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}
	
	public String getDepartmentName()
	{
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
	
	public int getTotalMembers()
	{
		return totalMembers;
	}
	
	public void setTotalMembers(int totalMembers)
	{
		this.totalMembers = totalMembers;
	}
	
	public void inputData(Scanner sc)
	{
		this.departmentId = inputDepartmentId(sc);
		this.departmentName = inputDepartmentName(sc);
		this.totalMembers = 0;
	}
	
	private String inputDepartmentId(Scanner sc)
	{
		System.out.println("Nhập vào mã phòng ban: ");
		do
		{
			String depId = sc.nextLine();
			if (depId.isBlank())
			{
				System.err.println("Không được để trống");
			}
			else
			{
				boolean isExist = DepartmentFeatureImpl.departments.stream()
						  .anyMatch(item -> item.getDepartmentId().equals(depId));
				if (isExist)
				{
					System.err.println("Đã tồn tại mã phòng ban");
				}
				else
				{
					return depId;
				}
			}
		}
		while (true);
	}
	
	private String inputDepartmentName(Scanner sc)
	{
		System.out.println("Nhập vào tên phòng ban: ");
		do
		{
			String depName = sc.nextLine();
			if (depName.isBlank())
			{
				System.err.println("Không được để trống");
			}
			else
			{
				return depName;
			}
		}
		while (true);
	}
	
	public void displayData()
	{
		System.out.printf(
				  "[ ID: %s | Name: %s | totalMembers: %d ]\n",
				  this.departmentId,
				  this.departmentName,
				  this.totalMembers
		);
	}
	
}
