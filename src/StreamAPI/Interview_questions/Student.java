package StreamAPI.Interview_questions;


import java.util.*;
import java.util.stream.Collectors;

public class Student {

    private int id;

    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private String departmentName;

    private int joinedYear;

    private String city;

    private int rank;

    public Student(int id, String firstName, String lastName, int age, String gender, String departmentName,
                   int joinedYear, String city, int rank) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.departmentName = departmentName;
        this.joinedYear = joinedYear;
        this.city = city;
        this.rank = rank;
    }

    public static void main(String[] args) {

        List<Student> list = Arrays.asList(
                new Student(1, "Rohit", "Mall", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
                new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
                new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
                new Student(4, "Satish Ray", "Malaghan", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
                new Student(5, "Roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
                new Student(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
                new Student(7, "Arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
                new Student(8, "Nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
                new Student(9, "Sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
                new Student(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98));


        // 1 - Students whose first name starts with alphabet A
        List<Student> nameStartsWithA = list.stream().filter(i -> i.getFirstName().startsWith("S")).collect(Collectors.toList());

        // 2 - Group The Student By Department Names
         /*
           sample output : -
           { Electronics Engineering=[Employee [id=7, firstName=Arun, lastName=Vittal, age=26, gender=Male, departmentName=Electronics Engineering, joinedYear=2014, city=Karnataka, rank=324]], Instrumentation Engineering=[Employee [id=10, firstName=Shubham, lastName=Pandey, age=26, gender=Male, departmentName=Instrumentation Engineering, joinedYear=2017, city=Mumbai, rank=98]], Biotech Engineering=[Employee [id=5, firstName=Roshan, lastName=Mukd, age=23, gender=Male, departmentName=Biotech Engineering, joinedYear=2022, city=Mumbai, rank=12]], Mechanical Engineering=[Employee [id=1, firstName=Rohit, lastName=Mall, age=30, gender=Male, departmentName=Mechanical Engineering, joinedYear=2015, city=Mumbai, rank=122], Employee [id=3, firstName=Ankit, lastName=Patil, age=25, gender=Female, departmentName=Mechanical Engineering, joinedYear=2019, city=Kerala, rank=164], Employee [id=4, firstName=Satish Ray, lastName=Malaghan, age=30, gender=Male, departmentName=Mechanical Engineering, joinedYear=2014, city=Kerala, rank=26], Employee [id=6, firstName=Chetan, lastName=Star, age=24, gender=Male, departmentName=Mechanical Engineering, joinedYear=2023, city=Karnataka, rank=90]], Computer Engineering=[Employee [id=2, firstName=Pulkit, lastName=Singh, age=56, gender=Male, departmentName=Computer Engineering, joinedYear=2018, city=Delhi, rank=67], Employee [id=8, firstName=Nam, lastName=Dev, age=31, gender=Male, departmentName=Computer Engineering, joinedYear=2014, city=Karnataka, rank=433], Employee [id=9, firstName=Sonu, lastName=Shankar, age=27, gender=Female, departmentName=Computer Engineering, joinedYear=2018, city=Karnataka, rank=7]]}
         */
        Map<String, List<Student>> mapData = list.stream().collect(Collectors.groupingBy(student -> student.getDepartmentName()));
        // list.stream().collect(Collectors.groupingBy(Student::getDepartmentName));
        System.out.println(mapData);


        // 3 - Find the total count of Student using stream
        long countStudent = list.stream().count();
        System.out.println("Total count of students : " + countStudent);


        // 4 - Find the max age of Student
        OptionalInt maxAge = list.stream().mapToInt(dt -> dt.getAge()).max();
        System.out.println("Max age of student : " + maxAge.getAsInt());
        // or
        int maxAgeValue = list.stream().map(dt -> dt.getAge()).max((a,b)->a-b).get();
        System.out.println(maxAgeValue);

        Integer max = list.stream().map(i -> i.getAge()).max((a, b) -> (a - b)).get();
        System.out.println(max);

        // 5 - Find all departments names
        /*
          [Mechanical Engineering, Computer Engineering, Biotech Engineering, Electronics Engineering, Instrumentation Engineering]
         */
        List<String> lstDepartments = list.stream().map(dt -> dt.getDepartmentName()).distinct()
                .collect(Collectors.toList());
        System.out.println("All distinct department names : " + lstDepartments);

        // 6 - Find the count of student in each department
        /*
          {Electronics Engineering=1, Instrumentation Engineering=1, Biotech Engineering=1, Mechanical Engineering=4, Computer Engineering=3}
         */
        Map<String, Long> countStudentInEachDept = list.stream()
                .collect(Collectors.groupingBy(Student::getDepartmentName, Collectors.counting()));
        System.out.println("Student count in each department : " + countStudentInEachDept);

        // 7 - Find the list of students whose age is less than 30
        List<Student> lstStudent = list.stream().filter(dt -> dt.getAge() < 30).collect(Collectors.toList());
        System.out.println("List of students whose age is less than 30 : "+lstStudent);

        // 9 - Find the average age of male and female students
        //{Female=26.0, Male=30.75}
        Map<String, Double> mapAvgAge = list.stream()
                .collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(Student::getAge)));
        System.out.println("Average age of male and female students : "+mapAvgAge);

        // 10 - Find the department who is having maximum number of students
        Map.Entry<String, Long> entry = list.stream()
                .collect(Collectors.groupingBy(Student::getDepartmentName, Collectors.counting())).entrySet().stream()
                .max(Map.Entry.comparingByValue()).get();

//        Map.Entry<String, Long> entry = list.stream()
//                .collect(Collectors.groupingBy(Student::getDepartmentName, Collectors.counting()))
//                .entrySet().stream()
//                .max((entry1, entry2) -> Long.compare(entry1.getValue(), entry2.getValue()))
//                .get();
        // Mechanical Engineering=4
        System.out.println("Department having maximum number of students : "+entry);

        // 11 - Find the Students who stays in Delhi and sort them by their names
        List<Student> lstDelhiStudent = list.stream().filter(dt -> dt.getCity().equals("Delhi"))
                .sorted(Comparator.comparing(Student::getFirstName)).collect(Collectors.toList());
        System.out.println("List of students who stays in Delhi and sort them by their names : "+lstDelhiStudent);

        // or
        List<Student> lstDelhiStudent1 = list.stream().filter(dt -> dt.getCity().equals("Delhi"))
                .sorted((a,b)->(a.getFirstName().compareTo(b.getFirstName()))).collect(Collectors.toList());
        System.out.println("List of students who stays in Delhi and sort them by their names : "+lstDelhiStudent1);

        // or (str1, str2) -> str1. compareToIgnoreCase(str2)
        List<Student> lstDelhiStudent2 = list.stream().filter(dt -> dt.getCity().equals("Delhi"))
                .sorted((p1, p2) -> p1.getFirstName().compareToIgnoreCase(p2.getFirstName())).collect(Collectors.toList());
        System.out.println(lstDelhiStudent2);
        System.out.println();


        // 12 - Find the average rank in all departments
        Map<String, Double> collect = list.stream()
                .collect(Collectors.groupingBy(Student::getDepartmentName, Collectors.averagingInt(Student::getRank)));
        System.out.println("Average rank in all departments  : "+collect);


        // 13 - Find the highest rank in each department
        /*
        Highest rank in each department  : {Electronics Engineering=Optional[Employee [id=7, firstName=Arun, lastName=Vittal, age=26, gender=Male, departmentName=Electronics Engineering, joinedYear=2014, city=Karnataka, rank=324]], Instrumentation Engineering=Optional[Employee [id=10, firstName=Shubham, lastName=Pandey, age=26, gender=Male, departmentName=Instrumentation Engineering, joinedYear=2017, city=Mumbai, rank=98]], Biotech Engineering=Optional[Employee [id=5, firstName=Roshan, lastName=Mukd, age=23, gender=Male, departmentName=Biotech Engineering, joinedYear=2022, city=Mumbai, rank=12]], Mechanical Engineering=Optional[Employee [id=4, firstName=Satish Ray, lastName=Malaghan, age=30, gender=Male, departmentName=Mechanical Engineering, joinedYear=2014, city=Kerala, rank=26]], Computer Engineering=Optional[Employee [id=9, firstName=Sonu, lastName=Shankar, age=27, gender=Female, departmentName=Computer Engineering, joinedYear=2018, city=Karnataka, rank=7]]}
         */
        Map<String, Optional<Student>> studentData = list.stream().collect(Collectors.groupingBy(Student::getDepartmentName,
                Collectors.minBy(Comparator.comparing(Student::getRank))));
        System.out.println("Highest rank in each department  : "+studentData);

        // or
        Map<String, Optional<Student>> studentData2 = list.stream().collect(Collectors.groupingBy(Student::getDepartmentName,
                Collectors.minBy((a,b)->(a.getRank()-b.getRank()))));
        System.out.println("Highest rank in each department  : "+studentData2);


        // 14- Find the list of students and sort them by their rank
        List<Student> stuRankSorted = list.stream().sorted(Comparator.comparing(Student::getRank))
                .collect(Collectors.toList());
        System.out.println("List of students sorted by their rank  : "+stuRankSorted);

        // 15- Find the student who has second rank
        Student student = list.stream().sorted(Comparator.comparing(Student::getRank)).skip(1).findFirst().get();
        System.out.println("Second highest rank student  : "+student);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getJoinedYear() {
        return joinedYear;
    }

    public void setJoinedYear(int joinedYear) {
        this.joinedYear = joinedYear;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
                + ", gender=" + gender + ", departmentName=" + departmentName + ", joinedYear=" + joinedYear + ", city="
                + city + ", rank=" + rank + "]";
    }

}