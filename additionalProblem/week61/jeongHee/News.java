import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //https://www.acmicpc.net/problem/1135
    //뉴스 전하기
    static int N;
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] childNum;

    static int init(int p) {
        int count = tree.get(p).size();
        for (int i = 0; i < tree.get(p).size(); i++) {
            count += init(tree.get(p).get(i));
        }
        return childNum[p] = count;
    }


    static int solution(int p, int count) {
        Collections.sort(tree.get(p), ((o1, o2) -> childNum[o2] - childNum[o1]));
        int ret = count;
        for (int i = 0; i < tree.get(p).size(); i++) {
            ret = Math.max(solution(tree.get(p).get(i), count + i + 1), ret);
        }
        return ret;
    }

    static int solution2(int now) {
        List<Integer> child = new ArrayList<>();
        for (int i = 0; i < tree.get(now).size(); i++) {
            child.add(solution2(tree.get(now).get(i)));
        }
        Collections.sort(child, Collections.reverseOrder());
        int ret = 0;
        for (int i = 0; i < child.size(); i++) {
            ret = Math.max(ret, child.get(i) + i);
        }
        return ret+1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        childNum = new int[N];
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(bf.readLine());
        st.nextToken();
        for (int i = 1; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            tree.get(p).add(i);
        }
//        init(0);
        System.out.println(solution2(0)-1);
    }
}