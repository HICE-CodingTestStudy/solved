package soobin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;

public class LateWork {

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashMap<String, String> hash = new HashMap<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            hash.put(st.nextToken(), st.nextToken());
        }

        List<Map.Entry<String, String>> remained = hash.entrySet().stream()
                .filter(entry -> entry.getValue().equals("enter"))
                .collect(Collectors.toList());
        Collections.sort(remained, new CustomComparator());

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

class CustomComparator implements Comparator<Map.Entry<String, String>> {

    @Override
    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
        return o2.getKey().compareTo(o1.getKey());
    }
}
