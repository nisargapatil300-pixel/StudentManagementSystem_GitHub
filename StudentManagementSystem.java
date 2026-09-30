import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    // Add Student
    static void addStudent(Scanner sc, ArrayList<Student> students) {

        sc.nextLine();

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter student age: ");
        int age = sc.nextInt();

        System.out.print("Enter student USN: ");
        String usn = sc.next();

        System.out.print("Enter student marks: ");
        double marks = sc.nextDouble();

        Student student = new Student(name, age, usn, marks);

        students.add(student);

        System.out.println("Student added successfully!");
    }


    // View Students
    static void viewStudents(ArrayList<Student> students) {

        System.out.println("\n--- All Students ---");

        if (students.isEmpty()) {

            System.out.println("No students found.");

        } else {

            for (Student s : students) {

                System.out.println("\nName: " + s.name);
                System.out.println("Age: " + s.age);
                System.out.println("USN: " + s.usn);
                System.out.println("Marks: " + s.marks);
            }
        }
    }


    // Search Student
    static void searchStudent(Scanner sc, ArrayList<Student> students) {

        System.out.print("Enter USN to search: ");
        String searchUsn = sc.next();

        boolean found = false;

        for (Student s : students) {

            if (s.usn.equals(searchUsn)) {

                System.out.println("\n--- Student Found ---");
                System.out.println("Name: " + s.name);
                System.out.println("Age: " + s.age);
                System.out.println("USN: " + s.usn);
                System.out.println("Marks: " + s.marks);

                found = true;
                break;
            }
        }

        if (!found) {

            System.out.println("Student not found.");
        }
    }


    // Delete Student
    static void deleteStudent(Scanner sc, ArrayList<Student> students) {

        System.out.print("Enter USN to delete: ");
        String deleteUsn = sc.next();

        boolean deleted = false;

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).usn.equals(deleteUsn)) {

                students.remove(i);

                System.out.println("Student deleted successfully!");

                deleted = true;
                break;
            }
        }

        if (!deleted) {

            System.out.println("Student not found.");
        }
    }


    // Update Student
    static void updateStudent(Scanner sc, ArrayList<Student> students) {

        System.out.print("Enter USN to update: ");
        String updateUsn = sc.next();

        boolean updated = false;

        for (Student s : students) {

            if (s.usn.equals(updateUsn)) {

                System.out.print("Enter new marks: ");
                s.marks = sc.nextDouble();

                System.out.println("Student updated successfully!");

                updated = true;
                break;
            }
        }

        if (!updated) {

            System.out.println("Student not found.");
        }
    }


    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent(sc, students);
                    break;

                case 2:
                    viewStudents(students);
                    break;

                case 3:
                    searchStudent(sc, students);
                    break;

                case 4:
                    deleteStudent(sc, students);
                    break;

                case 5:
                    updateStudent(sc, students);
                    break;

                case 6:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}