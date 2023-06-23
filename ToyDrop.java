package Final_control_work_2;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;

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

    public void setDropRate(int dropRate) {
        this.dropRate = dropRate;
    }
}

class ToyComparator implements Comparator<Toy> {
    public int compare(Toy toy1, Toy toy2) {
        return toy1.getDropRate() - toy2.getDropRate();
    }
}

public class ToyDrop {
    public static void main(String[] args) {
        Toy toy1 = new Toy(1, "Кукла", 10);
        Toy toy2 = new Toy(2, "Мяч", 5);
        Toy toy3 = new Toy(3, "Лего", 20);

        PriorityQueue<Toy> toyQueue = new PriorityQueue<>(new ToyComparator());
        toyQueue.add(toy1);
        toyQueue.add(toy2);
        toyQueue.add(toy3);

        try (FileWriter writer = new FileWriter("result.txt")) {
            for (int i = 0; i < 10; i++) {
                Toy currentToy = toyQueue.peek();

                // Проверка элементов с наименьшим dropRate на наличие дубликатов
                Toy lowestDropRateToy = currentToy;
                PriorityQueue<> tempQueue = new PriorityQueue<>(toyQueue);
                tempQueue.poll(); // Удаляем самый первый элемент
                while (!tempQueue.isEmpty()) {
                    Toy toy = tempQueue.poll();
                    if (toy.getDropRate() == currentToy.getDropRate()) {
                        lowestDropRateToy = toy;
                        break;
                    }
                }

                // Удаляем элемент с наименьшим dropRate
                toyQueue.remove(lowestDropRateToy);

                // Записываем значение в файл и обновляем dropRate
writer.write(lowestDropRateToy.getName() + "\n");
lowestDropRateToy.setDropRate(lowestDropRateToy.getDropRate() * 2);

                // Возвращаем элемент обратно в очередь
                toyQueue.offer(lowestDropRateToy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



