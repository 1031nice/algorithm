package boj.indextree;

import java.io.*;

public class 구간합구하기_2042 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = br.readLine().split(" ");
        long[] nums = new long[Integer.parseInt(s[0]) + 1];
        long[] tree = new long[nums.length*4];
        int queryAndUpdate = Integer.parseInt(s[1]) + Integer.parseInt(s[2]);
        for(int i=1; i<nums.length; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        build(tree, nums, 1, 1, nums.length-1);
        for(int i=0; i<queryAndUpdate; i++){
            s = br.readLine().split(" ");
            if(s[0].equals("1")) { // update
                long before = nums[Integer.parseInt(s[1])];
                nums[Integer.parseInt(s[1])] = Long.parseLong(s[2]);
                update(tree, nums, 1, nums.length-1, 1, Integer.parseInt(s[1]), Long.parseLong(s[2]) - before);

            }
            else { // query
                bw.write(query(tree, 1, Integer.parseInt(s[1]), Integer.parseInt(s[2]), 1, nums.length-1) + "\n");
            }
        }

        bw.flush();
    }

    private static long build(long[] tree, long[] nums, int index, int start, int end) {
        if(start == end) {
            tree[index] = nums[start];
            return nums[start]; // 리턴 필요없음
        }
        int mid = (start + end) / 2;
        return tree[index] = build(tree, nums, index*2, start, mid)
                + build(tree, nums, index*2+1, mid+1, end);
    }
    private static long query(long[] tree, int root, int left, int right, int start, int end) {
        if(end < left || start > right)
            return 0;
        else if(left <= start && end <= right) {
            return tree[root];
        }
        int mid = (start + end) / 2;
        return query(tree, root*2, left, right, start, mid) +
                query(tree, root*2+1, left, right, mid+1, end);
    }

    // nums[]의 index번째 수를 newValue로 바꾼다
    // 그럼 tree에서 해당 수의 위치가 어딘지를 알아야할텐데
    private static void update(long[] tree, long[] nums, int start, int end, int root, int index, long diff) {
        if(index < start || end < index)
            return;
        tree[root] += diff;
        if(start != end) {
            int mid = (start + end) / 2;
            update(tree, nums, start, mid, root*2, index, diff);
            update(tree, nums, mid + 1, end, root*2+1, index, diff);
        }
    }

}
