package soobin;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Olympic {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(br.readLine());
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] target = {};
        List<int[]> medals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            sc = new Scanner(br.readLine());
            int nation = sc.nextInt();
            int gold = sc.nextInt();
            int silver = sc.nextInt();
            int bronze = sc.nextInt();
            int[] medal = {nation, gold, silver, bronze};
            if (nation == K) target = medal;
            medals.add(medal);
        }

        Collections.sort(medals, (o1, o2) -> {
           if(o1[1] != o2[1]) return o1[1] > o2[1] ? -1 : 1;
           if(o1[2] != o2[2]) return o1[2] > o2[2] ? -1 : 1;
           return o1[3] > o2[3] ? -1 : 1;
        });

        int grade = 1;
        for (int[] medal : medals) {
            if (medal[0] == K) break;
            if (medal[1] != target[1] || medal[2] != target[2] || medal[3] != target[3]) grade++;
        }

        bw.write(String.valueOf(grade));
        bw.flush();
    }
}
