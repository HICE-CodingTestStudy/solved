package soobin;

import java.io.*;
import java.util.*;

public class ColorWeakness {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static boolean isSameArea(char[][] painting, int[] node, int adjRow, int adjCol) {
        if (adjRow < 0 || adjCol < 0 || adjRow >= painting.length || adjCol >= painting.length) return false;
        return painting[node[0]][node[1]] == painting[adjRow][adjCol];
    }

    private static int[] getUnvisited(boolean[][] visited) {
        int N = visited.length;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (!visited[i][j]) return new int[]{i, j};
        return new int[]{-1, -1};
    }

    private static int countArea(char[][] painting) {
        boolean[][] visited = new boolean[painting.length][painting.length];
        Queue<int[]> queue = new LinkedList<>();

        int count = 0;
        while (true) {
            int[] unvisited = getUnvisited(visited);
            if (unvisited[0] == -1) break;

            count++;
            queue.add(unvisited);
            visited[unvisited[0]][unvisited[1]] = true;
            while (!queue.isEmpty()) {
                int[] node = queue.poll();
                int row = node[0], col = node[1];

                if (isSameArea(painting, node, row-1, col) && !visited[row-1][col]) {
                    queue.add(new int[]{row-1, col});
                    visited[row-1][col] = true;
                }
                if (isSameArea(painting, node, row+1, col) && !visited[row+1][col]) {
                    queue.add(new int[]{row+1, col});
                    visited[row+1][col] = true;
                }
                if (isSameArea(painting, node, row, col-1) && !visited[row][col-1]) {
                    queue.add(new int[]{row, col-1});
                    visited[row][col-1] = true;
                }
                if (isSameArea(painting, node, row, col+1) && !visited[row][col+1]) {
                    queue.add(new int[]{row, col+1});
                    visited[row][col+1] = true;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        char[][] nonCW = new char[N][N], CW = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char color = line.charAt(j);
                nonCW[i][j] = color;
                CW[i][j] = color == 'G' ? 'R' : color;
            }
        }

        int nonCWCount = countArea(nonCW);
        int CWCount = countArea(CW);
        bw.write(String.format("%d %d", nonCWCount, CWCount));
        bw.flush();
    }
}
