package quest1;

public abstract class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(int age, String name){
        this.name = name;
        this.age = age;
    }

    public abstract Ticket getTicketType();
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public int compareTo(Person coisa){
        return Integer.compare(coisa.age, this.age);
    }
    @Override
    public String toString() {
        return name + ":" + age + "[" + getTicketType() + "]";
    }
}
