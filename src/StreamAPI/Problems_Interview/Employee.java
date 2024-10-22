package StreamAPI.Problems_Interview;

// just a pojo for stream api problem
public class Employee {
    private int id;
    private String name;
    private int age;
    private String domain; // gmail.com,yahoo.com etc..

    public Employee(int id, String name, int age, String domain) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", domain='" + domain + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmail() {
        return "abc@abc.com";
    }
}
