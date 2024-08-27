package business.entity;

import business.feature.impl.DepartmentFeatureImpl;
import business.feature.impl.EmployeeFeatureImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class Employee
{
	private String employeeId;
	private String employeeName;
	private LocalDate birthday;
	private boolean sex;
	private double salary;
	private Employee manager;
	private Department department;
	
	public Employee()
	{
	}
	
	public Employee(String employeeid, String employeeName, LocalDate birthday, boolean sex, double salary, Employee manager, Department department)
	{
		this.employeeId = employeeid;
		this.employeeName = employeeName;
		this.birthday = birthday;
		this.sex = sex;
		this.salary = salary;
		this.manager = manager;
		this.department = department;
	}
	
	public String getEmployeeId()
	{
		return employeeId;
	}
	
	public void setEmployeeId(String employeeid)
	{
		this.employeeId = employeeid;
	}
	
	public String getEmployeeName()
	{
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName)
	{
		this.employeeName = employeeName;
	}
	
	public LocalDate getBirthday()
	{
		return birthday;
	}
	
	public void setBirthday(LocalDate birthday)
	{
		this.birthday = birthday;
	}
	
	public boolean isSex()
	{
		return sex;
	}
	
	public void setSex(boolean sex)
	{
		this.sex = sex;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	
	public Employee getManager()
	{
		return manager;
	}
	
	public void setManager(Employee manager)
	{
		this.manager = manager;
	}
	
	public Department getDepartment()
	{
		return department;
	}
	
	public void setDepartment(Department department)
	{
		this.department = department;
	}
	
	public void inputData(Scanner sc)
	{
		this.employeeId = inputEmployeeId(sc);
		this.employeeName = inputEmployeeName(sc);
		this.birthday = inputBirthDay(sc);
		this.sex = inputSex(sc);
		this.salary = inputSalary(sc);
		this.manager = inputManager(sc);
		this.department = inputDepartment(sc);
	}
	
	private String inputEmployeeId(Scanner sc)
	{
		System.out.println("Nhập mã nhân viên: ");
		do
		{
			String employeeId = sc.nextLine();
			if (employeeId.matches("^E\\w{4}$"))
			{
				boolean isExist = EmployeeFeatureImpl.employees.stream()
						  .anyMatch(item -> item.getEmployeeId().equals(employeeId));
				if (isExist)
				{
					System.err.println("Mã nhân viên đã tồn tại");
				}
				else
				{
					return employeeId;
				}
			}
			else
			{
				System.err.println("Mã nhân viên không khớp");
			}
		}
		while (true);
	}
	
	private String inputEmployeeName(Scanner sc)
	{
		System.out.println("Nhập tên nhân viên: ");
		do
		{
			String employeeName = sc.nextLine();
			if (employeeName.isBlank())
			{
				/**
				 *
				 * empty ""
				 * empty "   "
				 *
				 * */
				System.err.println("Không được để trống");
			}
			else
			{
				return employeeName;
			}
		}
		while (true);
	}
	
	/**
	 * <a href="https://stackoverflow.com/questions/8746084/string-to-localdate">String to LocalDate</a>
	 */
	private LocalDate inputBirthDay(Scanner sc)
	{
		System.out.println("Nhập vào ngày sinh (dd/MM/YYYY) : ");
		do
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String birthday = sc.nextLine();
			if (birthday.isBlank())
			{
				System.err.println("Không được để trống");
			}
			else
			{
				return LocalDate.parse(birthday, dtf);
			}
		}
		while (true);
	}
	
	private boolean inputSex(Scanner sc)
	{
		System.out.println("Nhập giới tính: ");
		do
		{
			String sex = sc.nextLine();
			if (sex.isBlank())
			{
				System.err.println("Không được để trống");
			}
			else
			{
				if (sex.equalsIgnoreCase("true") || sex.equalsIgnoreCase("false"))
				{
					return Boolean.parseBoolean(sex);
				}
				else
				{
					System.err.println("Nhập không đúng định dạng (Nam - true | Nữ - false): ");
				}
			}
		}
		while (true);
	}
	
	private double inputSalary(Scanner sc)
	{
		System.out.println("Nhập vào lương cơ bản: ");
		do
		{
			String salary = sc.nextLine();
			if (salary.isBlank())
			{
				System.err.println("Không được để trống");
			}
			else
			{
				double newSalary = Double.parseDouble(salary);
				if (newSalary > 0)
				{
					return newSalary;
				}
				else
				{
					System.err.println("Nhập vào giá lớn hơn 0");
				}
			}
		}
		while (true);
	}
	
	private Employee inputManager(Scanner sc)
	{
		// hiển thị danh sách employee
		EmployeeFeatureImpl.employees.forEach(item ->
		{
			System.out.printf("[ ID: %s | Name: %s ]\n", item.getEmployeeId(), item.getEmployeeName());
		});
		
		System.out.println("Nhập id người quản lý: ");
		do
		{
			String manageId = sc.nextLine();
			if (manageId.isBlank())
			{
				return null;
			}
			
			Optional<Employee> optionalEmployee = EmployeeFeatureImpl.employees.stream()
					  .filter(item -> item.getEmployeeId().equals(manageId))
					  .findFirst();
			if (optionalEmployee.isPresent())
			{
				return optionalEmployee.get();
			}
			else
			{
				System.err.println("Không tồn tại mã quản lý = " + manageId);
			}
		}
		while (true);
	}
	
	private Department inputDepartment(Scanner sc)
	{
		// show department
		DepartmentFeatureImpl.departments.forEach(item ->
		{
			System.out.printf("[ ID: %s | Name: %s ]\n", item.getDepartmentId(), item.getDepartmentName());
		});
		
		System.out.println("Nhập vào mã phòng ban: ");
		do
		{
			String depId = sc.nextLine();
			Optional<Department> optionalDepartment = DepartmentFeatureImpl.departments.stream()
					  .filter(item -> item.getDepartmentId().equals(depId))
					  .findFirst();
			if (optionalDepartment.isPresent())
			{
				// lấy ra department muốn thêm
				Department department = optionalDepartment.get(); // cái này là vừa mới được chọn
				// kiểm tra xem nhân viên này lúc đầu đã có phòng ban chưa
				// nếu có thì giảm totalMember - 1 (nêu là update thì this.department là cữ)
				if (this.department != null)
				{
					this.department.setTotalMembers(this.department.getTotalMembers() - 1);
				}
				// và tăng phòng ban mới lên + 1
				department.setTotalMembers(department.getTotalMembers() + 1);
				
				return optionalDepartment.get();
			}
			else
			{
				System.err.println("Không tồn tại mã phòng ban = " + depId);
			}
		}
		while (true);
	}
	
	
	public void displayData()
	{
		System.out.printf("[ ID: %s | Name: %s | dob: %s | Sex: %s | Salary: %f | Manager: %s | Dep: %s ]\n", this.employeeId, this.employeeName, this.birthday.toString(), this.sex ? "Nam" : "Nữ", this.salary, this.manager != null ? this.manager.getEmployeeName() : "None", this.department.getDepartmentName());
	}
}
