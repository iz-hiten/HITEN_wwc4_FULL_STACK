package aaa;
import java.util.*;

class Student {
    private String id;
    private String name;
    private int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public int getMarks() {
        return marks;
    }

    public String getType() {
        return "Undergrad";
    }

    public String toString() {
        return id + " " + name + " " + marks;
    }
}

class GraduateStudent extends Student {
    private String area;

    public GraduateStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }

    public String getRole() {
        return "Grad (" + area + ")";
    }
}

class HonorsStudent extends Student {
    private int bonusMarks;

    public HonorsStudent(String id, String name, int marks, int bonusMarks) {
        super(id, name, marks);
        this.bonusMarks = bonusMarks;
    }

    @Override
    public int getMarks() {
        return super.getMarks() + bonusMarks;
    }

}

class Repository<T> {
    private Map<String, T> data = new HashMap<>();

    public void save(String id, T obj) {
        data.put(id, obj);
    }

    public T find(String id) {
        return data.get(id);
    }

    public void delete(String id) {
        data.remove(id);
    }
    public T getTopper() {
        T topper = null;
        int highestMarks = -1;
        for (T obj : data.values()) {
                Student student = (Student) obj;
                if (student.getMarks() > highestMarks) {
                    highestMarks = student.getMarks();
                    topper = obj;
                }
        }
        return topper;
    }


}


public class Main {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();

        list.add(new Student("S1", "Yash", 85));
        list.add(new Student("S2", "Shubham", 90));
        list.add(new Student("S3", "Ansh", 88));
        list.add(new Student("S4", "Tarak", 92));
        list.add(new GraduateStudent("G1", "Rohit", 95, "AI"));

        Repository<Student> repo = new Repository<>();
        for (Student s : list) repo.save(s.getId(), s);

        System.out.println("ALL:");
        list.forEach(System.out::println);

        System.out.println("\nLOOKUP S2:");
        Student s = repo.find("S2");
    System.out.println(s != null ? s : "not found");

    Iterator<Student> it = list.iterator();
    while (it.hasNext()) {
        Student student = it.next();
        if (student.getMarks() < 75) {
            repo.delete(student.getId());
            it.remove();
        }
    }

    System.out.println("\n after REMOVAL:");
    list.forEach(System.out::println);

    Student topper = repo.getTopper();
    System.out.println("\nTOPPER:");    
    System.out.println(topper != null ? topper : "no students");

    }
}
