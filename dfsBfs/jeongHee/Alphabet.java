package algoStudy.week1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Alphabet {
    //https://www.acmicpc.net/problem/1987
    //알파벳
    public static int max =-1;
    public static void dfs(HashSet<Character> visitedAlphabet, char[][] graph, int R, int C, int x, int y){
        if(y-1>=0&&!visitedAlphabet.contains(graph[x][y-1])){
            char nextNode = graph[x][y-1];
            visitedAlphabet.add(nextNode);
            dfs(visitedAlphabet,graph,R,C,x,y-1);
            visitedAlphabet.remove(nextNode);
        }
        if(y+1<C&&!visitedAlphabet.contains(graph[x][y+1])){
            char nextNode = graph[x][y+1];
            visitedAlphabet.add(nextNode);
            dfs(visitedAlphabet,graph,R,C,x,y+1);
            visitedAlphabet.remove(nextNode);
        }
        if(x-1>=0&&!visitedAlphabet.contains(graph[x-1][y])){
            char nextNode = graph[x-1][y];
            visitedAlphabet.add(nextNode);
            dfs(visitedAlphabet,graph,R,C,x-1,y);
            visitedAlphabet.remove(nextNode);
        }
        if(x+1<R&&!visitedAlphabet.contains(graph[x+1][y])){
            char nextNode = graph[x+1][y];
            visitedAlphabet.add(nextNode);
            dfs(visitedAlphabet,graph,R,C,x+1,y);
            visitedAlphabet.remove(nextNode);
        }
        max = Math.max(max, visitedAlphabet.size());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = sc.next();
            for (int j = 0; j < C; j++) {
                char c = s.charAt(j);
                map[i][j]=c;
            }
        }
        HashSet<Character> visitedAlphabet = new HashSet<>();
        visitedAlphabet.add(map[0][0]);
        dfs(visitedAlphabet,map,R,C,0,0);
        System.out.println(max);

    }
}
