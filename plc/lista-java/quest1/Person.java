package quest1;


// obs interface Comparable
public abstract class Person {
    private int age;
    private String name;

    public Person() {}
    public Person(int age, String name){
        this.age = age;
        this.name = name;
    }
    
    public int compareTo (Person person){ return 0; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public abstract String getTicktType();

    @Override
    public String toString() {
        return name + ":" + age + "[" + getTicktType() + "]";
    }
}
