package quest1;


public class Child extends Person {
    
    public Child(int age, String name) {
        super(age, name);
    }
    @Override
    public Ticket getTicketType() {
        return Ticket.CHILD;
    }
}
