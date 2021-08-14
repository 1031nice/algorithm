package programmers.level2;

public class 거리두기확인하기 {
    int[] dirRow = {-1, 0, 1, 0, // 상하좌우 거리 1
            -1, 1, 1, -1, // 대각선
            -2, 0, 2, 0}; // 상하좌우 거리 2
    int[] dirCol = {0, 1, 0, -1,
            1, 1, -1, -1,
            0, 2, 0, -2};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int i=0; i<places.length; i++) { // 2차원
            boolean ok = true;
            for(int j=0; j<places[0].length; j++) { // 1차원
                for(int k=0; k<places[0][0].length(); k++) { // String
                    if(places[i][j].charAt(k) == 'P' && !check(places[i], j, k)) {
                        ok = false;
                        break;
                    }
                }
                if(!ok) break;
            }
            if(ok) answer[i] = 1;
        }
        return answer;
    }

    public boolean check(String[] place, int row, int col) {
        int newRow = 0;
        int newCol = 0;
        for(int i=0; i<12; i++) {
            newRow = row + dirRow[i];
            newCol = col + dirCol[i];
            if(isIndexOk(newRow, newCol)) {
                if(i < 4) {
                    if(place[newRow].charAt(newCol) == 'P')
                        return false;
                }
                else if(i < 8) {
                    if(place[newRow].charAt(newCol) == 'P' &&
                            (place[newRow].charAt(col) != 'X' || place[row].charAt(newCol) != 'X')) {
                        return false;
                    }
                }
                else {
                    if(place[newRow].charAt(newCol) == 'P' &&
                            place[row + dirRow[i-8]].charAt(col + dirCol[i-8]) != 'X') {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isIndexOk(int row, int col) {
        return row < 5 && col < 5 && row >= 0 && col >= 0;
    }
}
