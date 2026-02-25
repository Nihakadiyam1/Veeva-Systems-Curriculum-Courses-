import java.util.*;

class Student {
    String name;
    double cgpa;
    int id;

    Student(String name, double cgpa, int id) {
        this.name = name;
        this.cgpa = cgpa;
        this.id = id;
    }
}

public class StudentsYetToBeServed {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        PriorityQueue<Student> pq = new PriorityQueue<>((s1, s2) -> {

            // 1. Higher CGPA first
            if (Double.compare(s2.cgpa, s1.cgpa) != 0)
                return Double.compare(s2.cgpa, s1.cgpa);

            // 2. Name ascending case sensitive
            if (!s1.name.equals(s2.name))
                return s1.name.compareTo(s2.name);

            // 3. ID ascending order
            return Integer.compare(s1.id, s2.id);
        });

        for (int i = 0; i < n; i++) {

            String event = sc.next();

            if (event.equals("Enter")) {
                String name = sc.next();
                double cgpa = sc.nextDouble();
                int id = sc.nextInt();

                pq.add(new Student(name, cgpa, id));
            } else if (event.equals("Served")) {
                if (!pq.isEmpty())
                    pq.poll();
            }
        }

        // Printing remaining students
        if (pq.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            while (!pq.isEmpty()) {
                System.out.println(pq.poll().name);
            }
        }

        sc.close();
    }
}