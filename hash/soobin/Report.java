package soobin;

import java.util.*;
import java.util.stream.Collectors;

public class Report {
    public static List<Integer> solution(String[] id_list, String[] report, int k) {
        HashMap<String, List<String>> who = new HashMap<>();
        HashMap<String, Integer> reported = new HashMap<>();
        List<String> filteredReport = Arrays.stream(report).distinct().collect(Collectors.toList());
        List<Integer> answer = new ArrayList<>();

        for(String reportInfo : filteredReport) {
            StringTokenizer st = new StringTokenizer(reportInfo);
            String from = st.nextToken();
            String to = st.nextToken();

            reported.put(to, reported.getOrDefault(to, 0) + 1);
            who.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
        }

        HashSet<String> blocked = new HashSet<>();
        reported.entrySet().forEach(entry -> {
            if(entry.getValue() >= k) blocked.add(entry.getKey());
        });
        Arrays.stream(id_list).forEach(id -> {
            List<String> id_report = who.get(id);
            int count = id_report == null ? 0 : (int) id_report.stream().filter(i -> blocked.contains(i)).count();
            answer.add(count);
        });

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        System.out.println(solution(id_list, report, 2));
    }
}
