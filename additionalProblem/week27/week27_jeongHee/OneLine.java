package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OneLine {
    //https://www.acmicpc.net/problem/1138
    //한 줄로 서기
    static int N;
    static List<Integer> line = new ArrayList<>();
    static List<Integer> ans = new LinkedList<>();

    static void solution() {
        for (int i = line.size() - 1; i >= 0; i--) {
            ans.add(line.get(i), i + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            line.add(sc.nextInt());
        }
        solution();
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}
