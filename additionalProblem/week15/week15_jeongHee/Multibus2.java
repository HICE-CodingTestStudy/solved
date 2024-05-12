package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Multibus2 {
    //https://www.acmicpc.net/problem/18869
    //멀티버스 2
    static class Planet implements Comparable<Planet> {
        int index;
        int value;

        public Planet(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Planet o) {
            if (value != o.value)
                return value - o.value;
            return index - o.index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> hm = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < M; i++) {
            ArrayList<Planet> planet = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                planet.add(new Planet(j, Integer.parseInt(st.nextToken())));
            }
            Collections.sort(planet);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < planet.size(); j++) {
                sb.append(planet.get(j).index);
                if(j!=planet.size()-1 && planet.get(j).value==planet.get(j+1).value)
                    sb.append("e");
                sb.append(".");
            }
            String key = sb.toString();
            if (hm.containsKey(key)){
                ans+=hm.get(key);
                hm.put(key, hm.get(key) + 1);
            }
            else hm.put(key, 1);
        }
        System.out.println(ans);
    }
}