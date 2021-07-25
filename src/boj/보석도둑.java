package boj;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 보석도둑 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfJewelry = scanner.nextInt();
        int numOfBags = scanner.nextInt();
        PriorityQueue<Jewelry> jewelryQ = new PriorityQueue<>(new JewelryComparator());
        PriorityQueue<Integer> bags = new PriorityQueue<>();
        for(int i=0; i<numOfJewelry; i++) {
            jewelryQ.add(new Jewelry(scanner.nextInt(), scanner.nextInt()));
        }
        for(int i=0; i<numOfBags; i++) {
            bags.add(scanner.nextInt());
        }
        while(!bags.isEmpty()) {
            Integer poll = bags.poll();
        }
    }

    private static class JewelryComparator implements Comparator<Jewelry> {
        @Override
        public int compare(Jewelry o1, Jewelry o2) {
            if(o1.value == o2.value) {
                return o1.weight - o2.weight;
            }
            return o2.value - o1.value;
        }
    }

    private static class Jewelry {
        int weight;
        int value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
