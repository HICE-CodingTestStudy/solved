package soobin;

import java.util.*;
import java.util.stream.Collectors;

public class Report {
    public static List<Integer> solution(String[] id_list, String[] report, int k) {
        HashMap<String, List<String>> who = new HashMap<>();
        HashMap<String, Integer> reported = new HashMap<>();
        // 중복 신고 제외
        List<String> filteredReport = Arrays.stream(report).distinct().collect(Collectors.toList());
        List<Integer> answer = new ArrayList<>();

        for(String reportInfo : filteredReport) {
            // 공백 기준으로 신고한 사람, 받은 사람 분리
            StringTokenizer st = new StringTokenizer(reportInfo);
            String from = st.nextToken();
            String to = st.nextToken();

            // 신고 몇 번 받았는 지 저장
            reported.put(to, reported.getOrDefault(to, 0) + 1);
            // 내가 누구를 신고했는지 저장
            who.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
        }

        HashSet<String> blocked = new HashSet<>();
        // k번 이상 신고 받으면 블락 처리
        reported.entrySet().forEach(entry -> {
            if(entry.getValue() >= k) blocked.add(entry.getKey());
        });
        // 모든 사람에 대해 내가 신고한 사람이 블락 처리 됐으면 몇 명인지 안내 횟수 저장
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
