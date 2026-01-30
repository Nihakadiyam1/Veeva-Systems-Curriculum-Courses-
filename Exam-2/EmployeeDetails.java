import java.util.*;

class Employee {
    String EmpName;
    int salary;
    String joinDate;
}

public class EmployeeDetails {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Employee> list = new ArrayList<Employee>();

    public static void main(String[] args) {

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            Employee e = new Employee();

            System.out.print("Name of the Employee: ");
            e.EmpName = sc.next();

            System.out.print("Salary of the Employee: ");
            e.salary = sc.nextInt();

            System.out.print("Joining Date of the Employee: ");
            e.joinDate = sc.next();

            list.add(e);
        }

        int choice = 0;

        while (choice != 5) {
            System.out.println("\n1.Salary Ascending Order");
            System.out.println("2.Salary Descending Order");
            System.out.println("3.Join Year Ascending Order");
            System.out.println("4.Join Year Descending Order");
            System.out.println("5.Exit");
            System.out.print("Enter your wished choice: ");
            choice = sc.nextInt();

            if (choice == 1)
                Collections.sort(list, Comparator.comparingInt(a -> a.salary));

            else if (choice == 2)
                Collections.sort(list, (a, b) -> b.salary - a.salary);

            else if (choice == 3)
                Collections.sort(list, (a, b) -> convert(a.joinDate).compareTo(convert(b.joinDate)));

            else if (choice == 4)
                Collections.sort(list, (a, b) -> convert(b.joinDate).compareTo(convert(a.joinDate)));

            else if (choice == 5)
                break;

            display();
        }
    }

    static String convert(String date) {
        String[] parts = date.split("-");
        return parts[2] + parts[1] + parts[0];
    }

    static void display() {
        for (Employee e : list) {
            System.out.println(e.EmpName + " | " + e.salary + " | " + e.joinDate);
        }
    }
}