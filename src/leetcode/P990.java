package leetcode;

import java.util.Arrays;

// union-find
public class P990 {
    public boolean equationsPossible(String[] equations) {
        int[] parents = new int[26];
        Arrays.fill(parents, -1);

        // ==
        for(String eq : equations) {
            if(eq.charAt(1) != '!') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                union(parents, x - 'a', y - 'a');
            }
        }

        // !=
        for(String eq : equations) {
            if(eq.charAt(1) == '!') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                if(find(parents, x - 'a') == find(parents, y - 'a')) {
                    return false;
                }
            }
        }

        return true;
    }

    private void union(int[] parents, int n1, int n2) {
        int p1 = find(parents, n1);
        int p2 = find(parents, n2);
        if(p1 != p2) {
            if(parents[p1] > parents[p2]) { // p2 is bigger than p1
                parents[p2] += parents[p1];
                parents[p1] = p2;
            }
            else {
                parents[p1] += parents[p2];
                parents[p2] = p1;
            }
        }
    }

    private int find(int[] parents, int n) {
        if(parents[n] < 0) return n;
        return parents[n] = find(parents, parents[n]);
    }
}
