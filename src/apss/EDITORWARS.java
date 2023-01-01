package apss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class EDITORWARS {
    // disjoint 하다는건
    // 자기 자신의 여집합이기 때문에 자기 자신 외의 모든 disjoint set이 후보군이 된다.
    // 따라서 두개의 set이 있고 이 중 하나에 distjoint한 새로운 value가 들어오면
    // 그걸 새로운 set의 root로 삼는 것보다 기존의 set에 추가하는게 항상 최댓값을 얻을 수 있다.
    // A, B set이 있고, 새로운 노드 c가 A에 disjoint하다면 새로운 set C를 만들어서 c를 추가하는 것보다
    // B에 추가하는 게 언제나 size가 최대인 것을 얻는 방향이라는 거야.
    // 왜냐면 C로 새로 만들었다고해도 C에만 추가되는 경우는 없기 때문이지
    // 처음에 말했듯 후보는 자기 자신을 제외한 다른 모든 집합이기 때문에
    // C에도 들어갈 수 있으면 A에도 들어갈 수 있거나 B에도 들어갈 수 있어(A, C 또는 B, C)
    // 후보는 항상 C를 포함한 다른 누군가가 있는데 그럼 그 다른 누군가한테 더해지는 게 최댓값을 만드는 경우니까 C에 뭣하러 넣겠냐
    // 그래서 애초에 C를 만들면 안되는거야 최댓값을 찾는다는 단서하에서는.

    static class Node {
        public Node(int value) {
            this.value = value;
        }
        List<Node> children = new ArrayList<>();
        Node other;
        int value;
        public int size(){
            return children.size() + 1;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/APSS/APSS.inputs/input_EDITORWARS.txt"));
        ListIterator<String> iterator = lines.listIterator();
        int numOfCases = Integer.parseInt(iterator.next());

        for(int i=0; i<numOfCases; i++){

            List<Node> roots = new ArrayList<>();
            String next = iterator.next();
            String[] split = next.split(" ");
            int numOfUsers = Integer.parseInt(split[0]);
            int numOfComments = Integer.parseInt(split[1]);
            int [] users = new int[numOfUsers];
            boolean flag = true;
            int indexOfContradiction = -1;

            for(int j=0; j<numOfComments; j++){
                String next1 = iterator.next();
                String[] split1 = next1.split(" ");
                String type = split1[0];
                int user1 = Integer.parseInt(split1[1]);
                int user2 = Integer.parseInt(split1[2]);

                // 둘다 처음 등장하는 유저라면
                if(users[user1] == 0 && users[user2] == 0) {
                    Node node1 = new Node(user1);
                    Node node2 = new Node(user2);
                    if(type.equals("ACK")){ // 둘이 같은 set에 속한다면
                        node1.children.add(node2); // 부모-자식 관계로 만들고
                        roots.add(node1); // root 노드만 모은 set에 추가
                    }
                    else { // 둘이 다른 set에 속한다면
                        node1.other = node2; // 서로의 존재에 대해 알려주고
                        node2.other = node1;
                        roots.add(node1);
                        roots.add(node2); // root 노드만 모은 set에 추가
                    }
                    users[user1] = 1; // user1이 이제 set에 포함되어 있음을 표시
                    users[user2] = 1;
                }

                // 둘다 이미 set에 포함되어 있는 유저라면
                else if (users[user1] != 0 && users[user2] != 0){
                    // 두 유저가 속한 set의 루트를 찾고
                    Node root1 = findRoot(roots, user1);;
                    Node root2 = findRoot(roots, user2);;
                    if(type.equals("ACK")){ // 같은 set에 속한다고 했으니
                        if(root1.value != root2.value) { // 다르면 모순
                            indexOfContradiction = i+1;
                            flag = false;
                        }
                    }
                    else { // 다른 set에 속한다고 했으니
                        if(root1.value == root2.value) { // 같으면 모순
                            indexOfContradiction = i+1;
                            flag = false;
                        }
                    }
                }

                // 둘중 하나만 처음 등장
                else {
                    int newUser = 0;
                    int oldUser = 0;
                    newUser = (users[user1] == 0) ? user1 : user2; // user1이 없다면 user1이 newNode
                    oldUser = (users[user1] == 0) ? user2 : user1; // user1이 없다면 user2가 기존노드
                    Node newNode = new Node(newUser);
                    Node root = findRoot(roots, oldUser);
                    if(type.equals("ACK")){
                        root.children.add(newNode);
                    }
                    else {
                        if(root.other == null){ // set이 한 개인 경우 가능, 2개일 때부터는 항상 짝수개로 늘어남
                            newNode.other = root;
                            root.other = newNode;
                            roots.add(newNode);
                        }
                        else {
                            root.other.children.add(newNode);
                        }
                    }
                    users[newNode.value] = 1;
                }
            }

            if(flag) { // 모순이 없는 경우
                int max = 0; // 최대 set의 크기
                int all = 0; // 모든 set에 등장한 user의 수
                if(roots.size() == 1){ // nodes는 1개이거나 짝수이다
                    max = users.length;
                }
                else {
                    for (int k = 0; k < roots.size(); k = k + 2) { // 짝수인 경우
                        Node root = roots.get(k);
                        max += Math.max(root.size(), root.other.size());
                        all = all + root.size() + root.other.size();
                    }
                    if (users.length > all) { // 등장하지 않은 user는 모두 최대 set에 추가한다
                        max += users.length - all;
                    }
                }
                System.out.println("MAX PARTY SIZE IS " + max);
            }
            else { // 모순이 있는 경우
                System.out.println("CONTRADICTION AT " + indexOfContradiction);
            }
        }
    }

    private static Node findRoot(List<Node> roots, int user1) {
        Node ret = null;
        for(Node root : roots) {
            if (root.value == user1)
                ret = root;
            else
                for (Node child : root.children)
                    if (child.value == user1)
                        ret = root;
        }
        return ret;
    }

}
