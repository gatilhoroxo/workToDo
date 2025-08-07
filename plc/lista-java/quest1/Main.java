package quest1;

public class Main {
    public static void main(String[] args) {
        Queue<Person> queue = new Queue<>(5);

        queue.push(new Child(5, "Child 1"));
        queue.push(new Adult(30, "Adult 1"));
        queue.push(new Child(6, "Child 2"));
        queue.push(new Adult(20, "Adult 2"));
        queue.push(new Child(8, "Child 3"));

        while (!queue.isEmpty()) {
            Person p = queue.pop();
            System.out.println(p);
        }
    }
}
