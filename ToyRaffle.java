package Final_control_work_2;

import java.util.PriorityQueue;
import java.util.Comparator;

class Toy {
    private int id;
    private String name;
    private int dropRate;

    public Toy(int id, String name, int dropRate) {
        this.id = id;
        this.name = name;
        this.dropRate = dropRate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDropRate() {
        return dropRate;
    }
}

class ToyComparator implements Comparator<Toy> {
    public int compare(Toy toy1, Toy toy2) {
        return toy1.getDropRate() - toy2.getDropRate();
    }
}

public class ToyRaffle {
    public static void main(String[] args) {
        PriorityQueue<Toy> toyQueue = new PriorityQueue<>(new ToyComparator());

        Toy toy1 = new Toy(1, "Мягкая игрушка", 5);
        Toy toy2 = new Toy(2, "Конструктор", 10);
        Toy toy3 = new Toy(3, "Пазл", 3);

        toyQueue.offer(toy1);
        toyQueue.offer(toy2);
        toyQueue.offer(toy3);

        System.out.println("Очередь для розыгрыша игрушек:");
        while (!toyQueue.isEmpty()) {
            System.out.println(toyQueue.poll().getName());
        }
    }
}


