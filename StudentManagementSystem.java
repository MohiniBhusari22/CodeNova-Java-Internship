
import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    double marks;

    // Constructor
    Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    // Display student details
    void display() {
        System.out.println("Roll Number: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("----------------------");
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Student[] students = new Student[100];
        int count = 0;
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    if (count == students.length) {
                        System.out.println("Student storage is full!");
                        break;
                    }

                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    boolean exists = false;

                    for (int i = 0; i < count; i++) {
                        if (students[i].rollNo == roll) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Roll number already exists!");
                        break;
                    }

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Marks (0-100): ");
                    double marks = sc.nextDouble();

                    if (roll <= 0 || marks < 0 || marks > 100) {
                        System.out.println("Invalid roll number or marks!");
                        break;
                    }

                    students[count] = new Student(roll, name, marks);
                    count++;

                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("No student records found.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            students[i].display();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter roll number to update: ");
                    int updateRoll = sc.nextInt();
                    sc.nextLine();

                    boolean updated = false;

                    for (int i = 0; i < count; i++) {
                        if (students[i].rollNo == updateRoll) {
                            System.out.print("Enter new name: ");
                            students[i].name = sc.nextLine();

                            System.out.print("Enter new marks (0-100): ");
                            double newMarks = sc.nextDouble();

                            if (newMarks < 0 || newMarks > 100) {
                                System.out.println("Invalid marks!");
                            } else {
                                students[i].marks = newMarks;
                                System.out.println("Student updated successfully!");
                            }

                            updated = true;
                            break;
                        }
                    }

                    if (!updated) {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter roll number to delete: ");
                    int deleteRoll = sc.nextInt();

                    boolean deleted = false;

                    for (int i = 0; i < count; i++) {
                        if (students[i].rollNo == deleteRoll) {
                            for (int j = i; j < count - 1; j++) {
                                students[j] = students[j + 1];
                            }

                            students[count - 1] = null;
                            count--;

                            System.out.println("Student deleted successfully!");
                            deleted = true;
                            break;
                        }
                    }

                    if (!deleted) {
                        System.out.println("Student not found!");
                    }
                    break;

                case 5:
                    System.out.println("Exiting Student Management System...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
