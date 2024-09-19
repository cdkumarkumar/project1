import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class AttendanceManagementSystem {

    // Class representing a student
    static class Student {
        private String regNo;
        private String name;

        public Student(String regNo, String name) {
            this.regNo = regNo;
            this.name = name;
        }

        public String getRegNo() {
            return regNo;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Student{regNo='" + regNo + "', name='" + name + "'}";
        }
    }

    // Class for managing attendance
    static class AttendanceManager {
        private Map<String, Student> students = new HashMap<>();
        private Map<String, Set<String>> attendanceRecords = new HashMap<>();

        public void addStudent(Student student) {
            students.put(student.getRegNo(), student);
            attendanceRecords.put(student.getRegNo(), new HashSet<>());
        }

        public boolean markAttendance(String regNo, String date) {
            if (students.containsKey(regNo)) {
                Set<String> attendanceDates = attendanceRecords.get(regNo);
                return attendanceDates.add(date);
            }
            return false;
        }

        public void viewAttendance(String regNo) {
            if (students.containsKey(regNo)) {
                System.out.println("Attendance record for " + students.get(regNo).getName() + " (" + regNo + "):");
                Set<String> dates = attendanceRecords.get(regNo);
                if (dates.isEmpty()) {
                    System.out.println("No attendance recorded.");
                } else {
                    for (String date : dates) {
                        System.out.println(" - " + date);
                    }
                }
            } else {
                System.out.println("Student with registration number " + regNo + " does not exist.");
            }
        }

        public void listAllStudents() {
            System.out.println("List of all students:");
            for (Student student : students.values()) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        AttendanceManager manager = new AttendanceManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAttendance Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Attendance");
            System.out.println("4. List All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");
            int choice;

            // Input validation for menu option
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter registration number: ");
                    String regNo = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    if (regNo.isEmpty() || name.isEmpty()) {
                        System.out.println("Registration number and name cannot be empty.");
                        break;
                    }
                    Student student = new Student(regNo, name);
                    manager.addStudent(student);
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    System.out.print("Enter registration number: ");
                    regNo = scanner.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    if (regNo.isEmpty() || date.isEmpty()) {
                        System.out.println("Registration number and date cannot be empty.");
                        break;
                    }
                    if (manager.markAttendance(regNo, date)) {
                        System.out.println("Attendance marked successfully.");
                    } else {
                        System.out.println("Failed to mark attendance. Check if the student exists.");
                    }
                    break;

                case 3:
                    System.out.print("Enter registration number: ");
                    regNo = scanner.nextLine();
                    if (regNo.isEmpty()) {
                        System.out.println("Registration number cannot be empty.");
                        break;
                    }
                    manager.viewAttendance(regNo);
                    break;

                case 4:
                    manager.listAllStudents();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please choose a number between 1 and 5.");
            }
        }
    }
}
