package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Manitto {
    //https://www.acmicpc.net/problem/5107
    //마니또
    static int N;
    static HashMap<String, String> hashMap;

    static int isCirculated() {
        HashSet<String> visited = new HashSet<>();
        ArrayList<String> keys = new ArrayList<>(hashMap.keySet());
        int ret = 0;
        while (true) {
            if (keys.isEmpty()) return ret;
            String next;
            visited.add(keys.get(0));
            next = hashMap.get(keys.get(0));
            while (true) {
                if (visited.contains(next)) {
                    keys.removeAll(visited);
                    visited.clear();
                    ret++;
                    break;
                }
                visited.add(next);
                next = hashMap.get(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = 1;
        while (true) {
            hashMap = new HashMap<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) return;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                hashMap.put(st.nextToken(), st.nextToken());
            }
            System.out.println(testCaseNum++ + " " + isCirculated());
        }
    }
}
