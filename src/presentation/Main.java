package presentation;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println("""
					  				======================= MENU =======================
					  							1. Quản lý phòng ban
					  							2. Quản lý nhân sự
					  							3. Thoát
					  				====================================================
					  				Lựa chọn đê:
					  """);
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice)
			{
				case 1:
				{
					DepartmentManage menuDepartment = new DepartmentManage();
					menuDepartment.menuDepartment(sc);
					break;
				}
				case 2:
				{
					EmployeeManage employeeManage = new EmployeeManage();
					employeeManage.menuEmployee(sc);
					break;
				}
				case 3:
				{
					System.exit(0);
					break;
				}
				default:
					System.err.println("Nhập lại từ 1 -> 3 đê: ");
			}
		}
		while (true);
	}
}