import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> integers = new Deque<Integer>();


        integers.addFirst(1);
        System.out.println(integers.size());
        integers.addLast(2);
        System.out.println(integers.size());
        integers.addLast(3);
        System.out.println(integers.size());
        integers.addFirst(-1);

        for (Iterator<Integer> iterator = integers.iterator(); iterator.hasNext(); ) {
            int i = iterator.next();
            System.out.println(i);
        }

        System.out.println(integers.size());
        integers.isEmpty();
        integers.removeFirst();
        System.out.println(integers.size());
        integers.removeLast();
        System.out.println(integers.size());
        integers.removeFirst();
        System.out.println(integers.size());
        integers.removeFirst();
        System.out.println(integers.size());
    }
}
