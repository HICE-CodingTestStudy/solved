import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class ToDalgi {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String[] dictionary;
    private static List<Integer>[] wordGraph;
    private static int N, wordIdx, maxLength;

    public static void main(String[] args) throws Exception {
        parseInput();
        dfs(wordIdx, new boolean[N]);
        System.out.println(dictionary[wordIdx]);
    }

    private static void parseInput() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        String start = st.nextToken();
        dictionary = new String[N];
        wordGraph = new List[N];

        Map<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            wordGraph[i] = new ArrayList<>();
            String word = br.readLine();
            wordMap.put(word, i);
            dictionary[i] = word;
        }

        makeWordGraph(wordMap);
        wordIdx = wordMap.get(start);
        maxLength = start.length();
    }

    private static void makeWordGraph(Map<String, Integer> wordMap) {
        for (int i = 0; i < N; i++) {
            String word = dictionary[i];
            StringBuilder sb = new StringBuilder(word);
            for (int j = 0; j < word.length(); j++) {
                sb.deleteCharAt(j);
                if (wordMap.containsKey(sb.toString())) {
                    int parentIdx = wordMap.get(sb.toString());
                    wordGraph[parentIdx].add(i);
                }
                sb.insert(j, word.charAt(j));
            }
        }
    }

    private static void dfs(int idx, boolean[] visited) {
        visited[idx] = true;

        if (wordGraph[idx].isEmpty()) {
            if (maxLength < dictionary[idx].length()) {
                maxLength = dictionary[idx].length();
                wordIdx = idx;
            }
            return;
        }

        for (int next : wordGraph[idx]) {
            if (visited[next]) continue;
            dfs(next, visited);
        }
    }
}