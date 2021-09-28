package programmers.level2;

import java.util.ArrayList;

public class 방문길이 {
    static class Point {
        int row;
        int col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public boolean equals(Object o) {
            Point other = (Point)o;
            return (this.row == other.row && this.col == other.col);
        }
    }

    static class Edge {
        Point p1;
        Point p2;

        public Edge(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
        @Override
        public boolean equals(Object o) {
            Edge other = (Edge)o;
            return (this.p1.equals(other.p1) && this.p2.equals(other.p2)) ||
                    (this.p1.equals(other.p2) && this.p2.equals(other.p1));
        }
    }

    public int solution(String dirs) {
        int answer = 0;

        int[][] matrix = new int[11][11];
        ArrayList<Edge> visited = new ArrayList<>();
        int[] dirRow = {-1, 0, 1, 0}; // U, R, D, L
        int[] dirCol = {0, 1, 0, -1};

        int row = 5;
        int col = 5;
        for(int i=0; i<dirs.length(); i++) {
            int index = 0;
            switch(dirs.charAt(i)) {
                case 'U': index = 0; break;
                case 'R': index = 1; break;
                case 'D': index = 2; break;
                case 'L': index = 3; break;
            }

            int newRow = row + dirRow[index];
            int newCol = col + dirCol[index];
            if(isIndexOk(matrix, newRow, newCol)) {
                Edge now = new Edge(new Point(row, col), new Point(newRow, newCol));
                if(!hasVisited(visited, now)) {
                    visited.add(now);
                    answer++;
                }
                row = newRow;
                col = newCol;
            }
        }

        return answer;
    }

    public boolean hasVisited(ArrayList<Edge> list, Edge edge) {
        for(Edge e : list) {
            if(e.equals(edge))
                return true;
        }
        return false;
    }

    public boolean isIndexOk(int[][] matrix, int row, int col) {
        return row >= 0 && col >= 0 && row < matrix.length && col < matrix[row].length;
    }
}
