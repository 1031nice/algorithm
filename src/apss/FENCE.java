package apss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class FENCE {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/APSS.inputs/fence.txt"));
        Iterator<String> iterator = lines.iterator();
        int numOfTests = Integer.parseInt(iterator.next());
        for(int i=0; i<numOfTests; i++){
            int numOfBars = Integer.parseInt(iterator.next());
            int[] bars = new int[numOfBars];
            String [] stringBars = iterator.next().split(" ");
            for(int j=0; j<numOfBars; j++){
                bars[j] = Integer.parseInt(stringBars[j]);
            }
            // -----입력 끝-----

            // -----출력-----
//            System.out.println(func(bars));
            System.out.println(solve_nlogn(bars, 0, bars.length-1));
        }
    }

    private static int solve_nlogn(int[] bars, int start, int end) {
        if(start == end)
            return bars[start];
        else if(end < start)
            return Integer.MAX_VALUE;
        int mid = start + (end-start)/2;
        int leftMax = solve_nlogn(bars, start, mid);
        int rightMax = solve_nlogn(bars, mid+1, end);
        int minBar = Math.min(bars[mid], bars[mid+1]); // 높이가 가장 작은 판자가 직사각형의 높이가 된다
        int count = 2;
        int overlapMax = minBar * count;
        // 비교를 해줘야지 매번, 현재까지 고른 판자에서 직사각형 넓이 구해놓고 하나씩 선택해가면서 뭐가 최대인지
        // 자, 근데 모든 왼쪽 판자에 대하여 모든 오른쪽 판자를 정말 꼭 다봐야 할까?
        // 다 보는 경우라면 O(N^2)이기 때문에 분할정복의 이점이 없다
        // 잘 생각해보면 항상 가장 큰 판자를 골라가는 경우만 따져보면된다
        // 그리고 매번 최댓값과 비교하여 최댓값보다 클 경우 갱신해주면 된다
        // 그럼 O(N)이 되므로 전체 과정을 O(NlogN)에 끝낼 수 있다
        int left = mid-1, right = mid+1+1;
        while(true) {
            int newBar;
            if(left >= 0 && right < bars.length) // 둘다 유효하다면 둘을 비교한다
                newBar = Math.max(bars[left--], bars[right++]); // 큰 판자를 고른다
            else if(left >= 0) // 왼쪽만 유효하다면
                newBar = bars[left--];
            else if(right < bars.length) // 오른쪽만 유효하다면
                newBar = bars[right++];
            else
                break;
            count++;
            minBar = Math.min(newBar, minBar); // 새로운 판자까지 꼈을 때 가장 낮은 높이를 고른다
            int temp = minBar * count;
            overlapMax = Math.max(temp, overlapMax);
        }
        return Math.max(overlapMax, Math.max(leftMax, rightMax));
    }

    // O(n^2)인데다가 for문 두개면 될걸 굳이 while로
    private static int solve_nn(int[] bars) {
        int max = Integer.MIN_VALUE;
        for(int i=0; i<bars.length; i++){
            int left = i-1; int right = i+1;
            int currentBar = bars[i];
            int leftSum = 0; int rightSum = 0;

            // 기준 막대의 왼쪽 구간에서 기준 막대의 길이를 높이로 하는 직사각형의 합 구하기
            while(left >= 0){
                if(bars[left] >= currentBar) {
                    leftSum += currentBar;
                    left--;
                }
                else
                    break;
            }

            // 기준 막대의 오른쪽 구간에서 기준 막대의 길이를 높이로 하는 직사각형의 합 구하기
            while(right < bars.length){
                if(bars[right] >= currentBar) {
                    rightSum += currentBar;
                    right++;
                }
                else
                    break;
            }

            max = Math.max(max, leftSum + rightSum + currentBar);
        }
        return max;
    }

}
