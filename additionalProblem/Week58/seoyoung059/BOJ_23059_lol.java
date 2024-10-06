package Ing.Week58.seoyoung059;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_23059_lol {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        Map<String, Integer> indegree = new HashMap<>();
        StringTokenizer st;
        String a, b;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = st.nextToken();
            b = st.nextToken();
            if(map.containsKey(a)){
                map.get(a).add(b);
            } else {
                Set<String> set = new HashSet<>();
                set.add(b);
                map.put(a, set);
            }

            if(!indegree.containsKey(a)){
                indegree.put(a, 0);
            }
            if(map.get(b)==null){
                map.put(b, new HashSet<>());
            }

            if(indegree.containsKey(b)){
                indegree.put(b, indegree.get(b)+1);
            } else {
                indegree.put(b, 1);
            }
        }

        PriorityQueue<String> pq = new PriorityQueue<>();
        ArrayDeque<String> q = new ArrayDeque<>();
        for(Map.Entry<String, Integer> entry: indegree.entrySet()){
            if(entry.getValue() == 0){
                q.add(entry.getKey());
            }
        }
        ArrayDeque<String> answer = new ArrayDeque<>();
        Set<String> set; String curr;
        while(!q.isEmpty()){
            while(!q.isEmpty()){
                pq.offer(q.poll());
            }
            while(!pq.isEmpty()) {
                curr = pq.poll();
                set = map.get(curr);
                answer.offer(curr);
                if (set == null) continue;
                for (String next : set) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        q.add(next);
                    }
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        if(answer.size() < indegree.size()){
            sb.append(-1);
        } else {
            while (!answer.isEmpty()) {
                sb.append(answer.poll()).append('\n');
            }
        }
        System.out.println(sb);

    }
}
