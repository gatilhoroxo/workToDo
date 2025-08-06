package quest1;


public class Child extends Person {
    
    public Child() {}
    public Child(int age, String name) {
        super(age, name);
    }
    @Override
    public String getTicktType() {
        return Ticket.CHILD.toString();
    }
}
