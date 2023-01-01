package apss;

import java.util.*;

public class NERD2 {

    static class Node {
        Node child;
        int num1;
        int num2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfApplicants = scanner.nextInt();
        Set<Node> roots = new HashSet<>();
        int ret = 0;
        for(int i=0; i<numOfApplicants; i++){
            int input1 = scanner.nextInt();
            int input2 = scanner.nextInt();
            boolean isNewRoot = true;
            for(Node node : roots){
                if(node.num1 > input1 && node.num2 > input2){
                    Node newNode = new Node();
                    newNode.child = node.child;
                    node.child = newNode;
                    isNewRoot = false;
                    break;
                }
                else if(node.num1 < input1 && node.num2 < input2){
                    Node newNode = new Node();
                    newNode.child = node;
                    isNewRoot = false;
                    break;
                }
            }
            if(isNewRoot){
                Node newRootNode = new Node();
                newRootNode.num1 = input1;
                newRootNode.num2 = input2;
                roots.add(newRootNode);
            }
            ret = ret + roots.size();
        }
        System.out.println(ret);
    }

}
