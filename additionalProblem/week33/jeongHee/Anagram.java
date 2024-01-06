import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //https://www.acmicpc.net/problem/6443
    //에너그램
    static int N;
    static Set<String> answers;
    static List<Character> alphabetsList;
    static Set<Character> alphabets;
    static Map<Character, Integer> alphaCount;

    static void init() {
        answers = new HashSet<>();
        alphabets = new HashSet<>();
        alphaCount = new HashMap<>();
        alphabetsList = new ArrayList<>();
    }

    static void getAlphabets(String s) {
        for (int i = 0; i < s.length(); i++) {
            alphabets.add(s.charAt(i));
            alphaCount.putIfAbsent(s.charAt(i), 0);
            alphaCount.put(s.charAt(i), alphaCount.get(s.charAt(i)) + 1);
        }
        alphabetsList = new ArrayList<>(alphabets);
        Collections.sort(alphabetsList);
    }

    static void getAnswer(Map<Character, Integer> visited, String ans, int length) {
        if (ans.length() == length) {
            if (!answers.contains(ans)) {
                answers.add(ans);
                System.out.println(ans);
            }
            return;
        }
        for (int i = 0; i < alphabetsList.size(); i++) {
            if (!Objects.equals(visited.get(alphabetsList.get(i)), alphaCount.get(alphabetsList.get(i)))) {
                visited.putIfAbsent(alphabetsList.get(i), 0);
                visited.put(alphabetsList.get(i), visited.get(alphabetsList.get(i)) + 1);
                getAnswer(visited, ans + alphabetsList.get(i), length);
                visited.put(alphabetsList.get(i), visited.get(alphabetsList.get(i)) - 1);
            }
        }
    }

    static void solution(String s) {
        init();
        getAlphabets(s);
        getAnswer(new HashMap<>(), "", s.length());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        while (N-- > 0) {
            solution(bf.readLine());
        }
    }
}
