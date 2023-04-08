package soobin;

import java.util.*;
import java.util.stream.Collectors;

public class Report {
    public static List<Integer> solution(String[] id_list, String[] report, int k) {
        HashMap<String, List<String>> who = new HashMap<>();
        HashMap<String, Integer> reported = new HashMap<>();
        List<Integer> answer = new ArrayList<>();

        for(String reportInfo : report) {
            StringTokenizer st = new StringTokenizer(reportInfo);
            String from = st.nextToken();
            String to = st.nextToken();

            if(who.get(from) != null && who.get(from).stream().anyMatch(to::equals)) continue;
            reported.put(to, reported.getOrDefault(to, 0) + 1);
            who.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
        }

        List<String> blocked = reported.entrySet().stream()
                .filter(entry -> entry.getValue() >= k)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .keySet().stream().collect(Collectors.toList());
        Arrays.stream(id_list).forEach((id)-> answer.add(getReportMail(who.get(id), blocked)));

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = {"con", "ryan", "apeach"};
        String[] report = {"con apeach", "ryan apeach", "con ryan", "ryan apeach", "apeach con", "apeach con"};
        System.out.println(solution(id_list, report, 2));
    }

    private static int getReportMail(List<String> reported, List<String> blocked) {
        return reported == null ? 0
                : reported.stream().flatMap(i -> blocked.stream().filter(j -> i.equals(j))).toArray().length;
    }
}
