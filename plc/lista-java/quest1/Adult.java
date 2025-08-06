package quest1;

public class Adult extends Person {

    public Adult() {}
    public Adult(int age, String name) {
        super(age, name);
    }
    @Override
    public String getTicktType() {
        return Ticket.ADULT.toString();
    }
}
