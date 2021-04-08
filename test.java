import java.util.Scanner;

public class test {
    //khai báo class student
    public static class Student {
        // khai báo id,name,birthDate,score
        private final int id;
        private String name;
        private String birthDate;
        private double score;
        // constructor của student
        public Student(String name, String birthDate, double score) {
            StudentManagement studentManagement = new StudentManagement();
            this.id = studentManagement.getNewId();
            this.name = name;
            this.birthDate = birthDate;
            this.score = score;
            studentManagement.addStudent(this);
        }
        // getter và setter của student
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }
    //khai báo class StudentManagement để add và edit
    public static class StudentManagement {
        private static int idCount = 0;
        private static int numOfStudents = 0;
        private static final Student[] students = new Student[10];

        public int getNewId() {
            idCount++;
            return idCount;
        }

        public void addStudent(Student student) {
            students[numOfStudents] = student;
            numOfStudents++;
        }

        public void displayStudentList() {
            System.out.printf("%-20s%-20s%-20s%-20s%n", "ID", "Name", "DOB", "Score");
            for (int i = 0; i < numOfStudents; i++) {
                Student student = students[i];
                System.out.printf("%-20s%-20s%-20s%-20s%n", student.getId(), student.getName(), student.getBirthDate(), student.getScore());
            }
            System.out.println();
        }

        public void editStudentList(int id, int editChoice, String newValue) {
            for (Student student : students) {
                if (student.getId() == id) {
                    switch (editChoice) {
                        case 1 :
                            student.setName(newValue);
                        case 2 :
                            student.setBirthDate(newValue);
                        case 3 :
                            student.setScore(Double.parseDouble(newValue));
                    }
                    break;
                }
            }
        }

        public void removeStudent(int id) {
            // duyệt mảng student
            for (int i = 0; i < numOfStudents; i++) {
                Student student = students[i];
                if (student.getId() == id) {
                    // phần tử trước sắp xếp = phần tử sau
                    for (int j = i; j < numOfStudents-1; j++) {
                        students[j] = students[j+1];
                    }
                    // danh sách sinh viên sau khi xóa -1
                    students[numOfStudents-1] = null;
                    numOfStudents--;
                    break;
                }
            }
        }

        public void findHighestScore() {
            double max = 0;
            Student highScoreStudent = null;
            for (int i = 0; i < numOfStudents; i++) {
                Student student = students[i];
                if (max < student.getScore()) {
                    max = student.getScore();
                    highScoreStudent = student;
                }
            }

            System.out.printf("%-20s%-20s%-20s%-20s%n", "ID", "Name", "DOB", "Score");
            if (highScoreStudent == null) {
                System.out.printf("%-20s%-20s%-20s%-20s%n", "N/A", "N/A", "N/A", "N/A");
            } else {
                System.out.printf("%-20s%-20s%-20s%-20s%n", highScoreStudent.getId(), highScoreStudent.getName(), highScoreStudent.getBirthDate(), highScoreStudent.getScore());
            }
        }

        public void sortByScore() {
            for (int i = 0; i < numOfStudents-1; i++) {
                for (int j = i+1; j < numOfStudents; j++) {
                    if (students[i].getScore() < students[j].getScore()) {
                        Student temp = students[i];
                        students[i] = students[j];
                        students[j] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        //add test students to array "students"
        new Student("Thanh", "01/01/1994", 3.3);
        new Student("Long", "01/01/1994", 4.2);
        new Student("Thao", "01/01/1990", 4.5);
        new Student("Quan", "01/01/1992", 3.5);

        Scanner scanner = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();

        int userChoice;
        do {
            System.out.println("1. Display student list");
            System.out.println("2. Add a new student");
            System.out.println("3. Edit student by ID");
            System.out.println("4. Remove student by ID");
            System.out.println("5. Find highest score");
            System.out.println("6. Sort by score");
            System.out.println("7. Exit");
            userChoice = scanner.nextInt();
            scanner.nextLine();
            switch (userChoice) {
                case 1 :
                    studentManagement.displayStudentList();
                case 2 :
                    {
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Day Of Birth:");
                    String birthDate = scanner.nextLine();
                    System.out.println("Enter score:");
                    double score = scanner.nextDouble();

                    new Student(name, birthDate, score);
                    studentManagement.displayStudentList();
                }
                case 3 :
                    {
                    System.out.println("Enter ID you want to edit");
                    int id = scanner.nextInt();
                    System.out.println("1. Edit name");
                    System.out.println("2. Edit DOB");
                    System.out.println("3. Edit score");
                    int editChoice = scanner.nextInt();
                    System.out.println("Enter new value");
                    scanner.nextLine();
                    String newValue = scanner.nextLine();

                    studentManagement.editStudentList(id, editChoice, newValue);
                    studentManagement.displayStudentList();
                }
                case 4 :
                    {
                    System.out.println("Enter ID you want to remove");
                    int id = scanner.nextInt();

                    studentManagement.removeStudent(id);
                    studentManagement.displayStudentList();
                }
                case 5 :
                    studentManagement.findHighestScore();
                case 6 :
                    {
                    studentManagement.sortByScore();
                    studentManagement.displayStudentList();
                }
                case 7 :
                    System.out.println("Exiting...");
            }
        } while (userChoice != 7);
    }
}