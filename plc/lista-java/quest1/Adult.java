package quest1;

public class Adult extends Person {

    public Adult(int age, String name) {
        super(age, name);
    }
    @Override
    public Ticket getTicketType() {
        return Ticket.ADULT;
    }
}
