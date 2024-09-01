package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: Department findById ===");
        Department department = departmentDao.findById(8);
        System.out.println(department);

        System.out.println("\n=== TEST 2: Department findAll ===");
        List<Department> list = departmentDao.findAll();
        for (Department d: list)
            System.out.println(d);

        System.out.println("\n=== TEST 3: Department Insert ===");
        Department newDepartment = new Department(null, "Journalism");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());

        System.out.println("\n=== TEST 4: Department Update ===");
        department = departmentDao.findById(3);
        department.setName("Architect");
        departmentDao.update(department);
        System.out.println("Update completed");

        System.out.println("\n=== TEST 5: Department Delete ===");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete completed");

        sc.close();
    }
}
