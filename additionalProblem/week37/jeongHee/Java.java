import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Java {
    //https://www.acmicpc.net/problem/25601
    //자바의 형변환
    static Map<String, List<String>> siblings = new HashMap<>();

    static boolean solution(String a, String b) {
        List<String> sib = siblings.get(a);
        HashSet<String> visited = new HashSet<>();
        for (String s : sib) {
            if (visited.contains(s)) continue;
            visited.add(s);
            Queue<String> queue = new ArrayDeque<>();
            queue.add(s);
            while (!queue.isEmpty()) {
                String now = queue.poll();
                if (now.equals(b)) return true;
                for (String next : siblings.get(now)) {
                    if (visited.contains(next)) continue;
                    queue.add(next);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            siblings.putIfAbsent(a, new ArrayList<>());
            siblings.putIfAbsent(b, new ArrayList<>());
            siblings.get(b).add(a);
        }
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String a = st.nextToken();
        String b = st.nextToken();
        if (solution(a, b) || solution(b, a)) System.out.println(1);
        else System.out.println(0);
    }
}
