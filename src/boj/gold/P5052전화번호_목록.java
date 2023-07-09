package boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P5052전화번호_목록 {
    static boolean insert(TrieNode root, String number) {
        TrieNode currentNode = root;

        for (int i = 0; i < number.length(); i++) {
            int index = number.charAt(i) - '0';

            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }

            currentNode = currentNode.children[index];

            if (currentNode.isEndOfNumber) {
                return false;
            }
        }

        currentNode.isEndOfNumber = true;

        for (int i = 0; i < 10; i++) {
            if (currentNode.children[i] != null) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(reader.readLine());
        for (int t = 0; t < TC; t++) {
            TrieNode root = null;
            boolean flag = true;
            int N = Integer.parseInt(reader.readLine());
            for (int i = 0; i < N; i++) {
                String number = reader.readLine();
                if (root == null) {
                    root = new TrieNode();
                }
                if (!insert(root, number)) {
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static class TrieNode {
        boolean isEndOfNumber;
        TrieNode[] children;

        TrieNode() {
            isEndOfNumber = false;
            children = new TrieNode[10];
        }
    }

}
