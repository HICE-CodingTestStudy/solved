package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JeomSooni {
    //https://www.acmicpc.net/problem/15824
    //너 봄에는 캡사이신이 맛있단다.
    static int N;
    static long division = 1000000007;
    static List<Long> scoville = new ArrayList<>();
    static long ans = 0;
    static long[] combinationCount;

    static void solution(int left, int right) {
        ans += ((scoville.get(right) - scoville.get(left)) * (combinationCount[right - left - 1] + 1)) % division;
    }

    //(최대,최소) 쌍을 모두 찾는다 -> 30만 제한은 불가한 풀이, 3천 제한은 가능
    //정렬한 스코빌 지수 리스트에서 해당 최소 인덱스, 최대 인덱스를 사이에 둔 메뉴의 개수에 따라
    //해당 최대, 최소 쌍에 대한 메뉴 조합이 정해진다.
    //n개로 만들 수 있는 1개 메뉴 조합, 2개 메뉴 조합 + ... n개 메뉴 조합의 총 수 = (n-1)조합개수 * 2 + 1
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        combinationCount = new long[N + 1];
        combinationCount[1] = 1;
        for (int i = 2; i <= N; i++) {
            combinationCount[i] = (combinationCount[i - 1] * 2 + 1) % division;
        }
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            scoville.add(Long.parseLong(st.nextToken()));
        }
        Collections.sort(scoville);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                solution(i, j);
            }
        }
        System.out.println(ans % division);
    }
}
