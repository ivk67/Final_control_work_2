package Final_control_work_2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ToyStore {
    private static final String FILE_NAME = "result.txt";
    
    private static PriorityQueue<Toy> toysQueue;
    
    public static void main(String[] args) {
        toysQueue = new PriorityQueue<>();
        
        System.out.println("Введите игрушки данные в формате: <id> <название> <частота выпадения>");
        System.out.println("Для завершения ввода введите пустую строку.");
        
        Scanner scanner = new Scanner(System.in);
        
        String input;
        do {
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                String[] parts = input.split(" ");
                
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int weight = Integer.parseInt(parts[2]);
                
                Toy toy = new Toy(id, name, weight);
                toysQueue.add(toy);
            }
        } while (!input.isEmpty());
        
        scanner.close();
        
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            
            for  (int i = 0; i < 10; i++) {                int randomWeight = (int) (Math.random() * 10) + 1;
                Toy toy = getToy(randomWeight);
                
                writer.write(toy.toString() + "\n");
            }
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Результаты записаны в файл " + FILE_NAME);
    }
    
    private static Toy getToy(int randomWeight) {
        int totalWeight = toysQueue.size();
        int accumulatedWeight = 0;
        
        for (Toy toy : toysQueue) {
            accumulatedWeight += toy.getWeight();
            
            if (randomWeight <= accumulatedWeight / totalWeight) {
                return toy;
            }
        }
        
        // В случае, если случайное значение не попало в заданные веса, вернем первый элемент очереди.
        return toysQueue.peek();
    }
    
    static class Toy implements Comparable<Toy> {
        private int id;
        private String name;
        private int weight;
        
        public Toy(int id, String name, int weight) {
            this.id = id;
            this.name = name;
            this.weight = weight;
        }
        
        public int getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        public int getWeight() {
            return weight;
        }
        
        @Override
        public String toString() {
            return id + " " /*+ name + " " + weight*/;
        }
        
        @Override
        public int compareTo(Toy other) {
            return Integer.compare(weight, other.getWeight());
        }
    }
}



