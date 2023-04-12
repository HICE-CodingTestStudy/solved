package soobin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;

public class LateWork {

    public static void solution() throws Exception {
        // Scanner, System.out.println 대신 입출력을 빠르게 할 수 있는 java class
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashMap<String, String> hash = new HashMap<>();
        int N = Integer.parseInt(br.readLine());

        // 공백 기준으로 split, 역시 split()보다 빠름
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            hash.put(st.nextToken(), st.nextToken());
        }

        // value가 "leaved"로 덮어씌워지지 않은 애들만 필터링
        List<Map.Entry<String, String>> remained = hash.entrySet().stream()
                .filter(entry -> entry.getValue().equals("enter"))
                .collect(Collectors.toList());

        // 이름 역순으로 정렬
        Comparator<Map.Entry<String, String>> comparator = new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        };
        Collections.sort(remained, comparator);

        for(Map.Entry<String, String> remain : remained) {
            bw.write(remain.getKey());
            bw.newLine();
        }
        bw.flush();
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
