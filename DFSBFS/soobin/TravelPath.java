package soobin;

import java.util.*;

public class TravelPath {
    private Map<String, List<String>> graph;
    private List<String> trace;
    private int totalTicket;

    private void dfs(String from, String to, int idx) {
        List<String> temp = graph.get(from);
        temp.set(idx, "VISITED");
        graph.put(from, temp);
        trace.add(to);

        if (graph.containsKey(to)) {
            List<String> tickets = graph.get(to);
            for (int i = 0; i < tickets.size(); i++) {
                if (tickets.get(i) == "VISITED") continue;

                dfs(to, tickets.get(i), i);
            }
        }

        if (trace.size() != totalTicket + 1) {
            trace.remove(trace.size() - 1);
            temp.set(idx, to);
            graph.put(from, temp);
        }
    }

    public List<String> solution(String[][] tickets) {
        graph = new HashMap<>();
        trace = new ArrayList<>();
        totalTicket = tickets.length;

        Arrays.sort(tickets, Comparator.comparing(t -> t[1]));
        for (String[] ticket : tickets)
            graph.computeIfAbsent(ticket[0], x -> new ArrayList<>()).add(ticket[1]);

        trace.add("ICN");
        for (int i = 0; i < graph.get("ICN").size(); i++)
            dfs("ICN", graph.get("ICN").get(i), i);
        return trace;
    }

    public static void main(String[] args) {
        TravelPath tp = new TravelPath();
        System.out.println(tp.solution(new String[][] {{"ICN", "A"}, {"ICN", "B"}, {"B", "ICN"}}));
    }
}
