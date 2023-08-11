package soobin;

import java.util.*;

public class WordConversion {
    private Map<String, List<String>> graph;
    private Set<String> visited;
    private Map<String, String> pred;

    private boolean isAdjacent(String source, String target) {
        int diff = 0;
        for (int i = 0; i < source.length(); i++)
            if (source.charAt(i) != target.charAt(i)) diff++;
        return diff == 1;
    }

    private int getPathLength(String target) {
        int answer = 0;
        while (pred.containsKey(target)) {
            target = pred.get(target);
            answer++;
        }
        return answer;
    }

    public int solution(String begin, String target, String[] words) {
        graph = new HashMap<>();
        visited = new HashSet<>();
        pred = new HashMap<>();

        graph.put(begin, new ArrayList<>());
        for (String word : words) graph.put(word, new ArrayList<>());
        for (String word : words) {
            if (isAdjacent(begin, word)) graph.get(begin).add(word);
            for (String other : words) {
                if (isAdjacent(word, other) && word != other) graph.get(word).add(other);
            }
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(begin);
        visited.add(begin);

        while (!queue.isEmpty()) {
            String word = queue.poll();

            for (String adj : graph.get(word)) {
                if (visited.contains(adj)) continue;

                queue.add(adj);
                visited.add(adj);
                pred.put(adj, word);
            }
        }
        return getPathLength(target);
    }

    public static void main(String[] args) {
        WordConversion wc = new WordConversion();
        System.out.println(wc.solution("hit", "cog",
                new String[]{"hot", "dot","dog","lot","log","cog"}));
        System.out.println(wc.solution("hit", "cog",
                new String[]{"hot", "dot","dog","lot","log"}));
    }
}
