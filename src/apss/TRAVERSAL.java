package apss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;

public class TRAVERSAL {

    public static void main(String[] args) throws IOException {
        // input
        List<String> lines = Files.readAllLines(Paths.get("./src/APSS/APSS.inputs/input_TRAVERSAL.txt"));
        ListIterator<String> iterator = lines.listIterator();
        int numOfNodes = Integer.parseInt(iterator.next());
        int[] front = new int[numOfNodes];
        int[] middle = new int[numOfNodes];
        int[] rear;
        for(int j=0; j<numOfNodes; j++){
            front[j] = Integer.parseInt(iterator.next());
        }
        for(int j=0; j<numOfNodes; j++){
            middle[j] = Integer.parseInt(iterator.next());
        }

        // solve
        rear = func(front, middle);

        // output
        for(int j=0; j<numOfNodes; j++){
            System.out.print(rear[j] + " ");
        }
    }

    private static int[] func(int[] front, int[] middle) {
        if(front.length == 0)
            return new int [0];
        int root = front[0];
        int index = indexOf(middle, root);
        int sizeOfLeft = index;
        int sizeOfRight = front.length - index - 1;

        // split front
        int[] leftOfFront = new int[sizeOfLeft];
        int[] rightOfFront = new int[sizeOfRight];
        split(front, leftOfFront, rightOfFront, "front");

        // split middle
        int[] leftOfMid = new int[sizeOfLeft];
        int[] rightOfMid = new int[sizeOfRight];
        split(middle, leftOfMid, rightOfMid, "middle");

        int[] leftOfRear = func(leftOfFront, leftOfMid);
        int[] rightOfRear = func(rightOfFront, rightOfMid);
        return make(root, leftOfRear, rightOfRear);
    }

    private static int[] make(int root, int[] leftOfRear, int[] rightOfRear) {
        int[] ret = new int[leftOfRear.length + rightOfRear.length + 1];
        for(int i =0; i<leftOfRear.length; i++){
            ret[i] = leftOfRear[i];
        }
        for(int i =0; i<rightOfRear.length; i++){
            ret[leftOfRear.length+i] = rightOfRear[i];
        }
        ret[ret.length-1] = root;
        return ret;
    }

    private static void split(int[] arr, int[] left, int[] right, String name) {
        if(name.equals("front")) {
            for (int i = 0; i < left.length; i++) {
                left[i] = arr[i+1];
            }
            for (int i = 0; i < right.length; i++){
                right[i] = arr[left.length+1+i];
            }
        }
        else { // middle
            for (int i = 0; i < left.length; i++) {
                left[i] = arr[i];
            }
            for (int i = 0; i < right.length; i++){
                right[i] = arr[left.length+1+i];
            }
        }
    }

    private static int indexOf(int[] middle, int element) {
        for(int i=0; i<middle.length; i++){
            if(middle[i] == element)
                return i;
        }
        return -1; // 못 찾으면 입력이 잘못된 경우이므로 고려하지 않도록 한다
    }
}
