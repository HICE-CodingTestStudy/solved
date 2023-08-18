package queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ChickenDelivery {
    //https://www.acmicpc.net/problem/15686
    //치킨 배달
    static class Coordinate {
        int i;
        int j;

        public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static ArrayList<Coordinate> house = new ArrayList<>();
    static ArrayList<Coordinate> chicken = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static void chooseChicken(boolean[][] visited, int[][][][] length, int count, int M, int chickenIdx) {
        if (count == M) {
            int result = 0;
            for (int i = 0; i < house.size(); i++) {
                Coordinate h = house.get(i);
                int hMin = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    Coordinate c = chicken.get(j);
                    if(!visited[c.i][c.j]) continue;
                    hMin = Math.min(hMin, length[h.i][h.j][c.i][c.j]);
                }
                result += hMin;
                if(result>=min) return;
            }
            min = Math.min(result,min);
        }
        for (int i = chickenIdx; i < chicken.size(); i++) {
            Coordinate c = chicken.get(i);
            if(visited[c.i][c.j]) continue;
            visited[c.i][c.j] = true;
            //다음을 탐색할때 이전것들을 건너뛰지 않은 채로 ( chickenIdx 없이) 선택시 시간초과
            chooseChicken(visited,length,count+1, M, i+1);
            visited[c.i][c.j] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) house.add(new Coordinate(i, j));
                if (map[i][j] == 2) chicken.add(new Coordinate(i, j));
            }
        }
        int[][][][] length = new int[N][N][N][N];
        for (int i = 0; i < house.size(); i++) {
            int iHouse = house.get(i).i;
            int jHouse = house.get(i).j;
            for (int j = 0; j < chicken.size(); j++) {
                int iChicken = chicken.get(j).i;
                int jChicken = chicken.get(j).j;
                length[iHouse][jHouse][iChicken][jChicken] = Math.abs(iHouse-iChicken)+ Math.abs(jHouse-jChicken);
            }
        }
        chooseChicken(new boolean[N][N], length, 0, M, 0);
        System.out.println(min);
    }
}
