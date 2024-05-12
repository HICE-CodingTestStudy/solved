package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class JeomSooni {
    //https://www.acmicpc.net/problem/15824
    //너 봄에는 캡사이신이 맛있단다.
    static int N;
    static long division = 1000000007;
    static List<Long> scoville = new ArrayList<>();
    static BigInteger ans = BigInteger.ZERO;
    static long[] combinationCount;

//    //(최대,최소) 쌍을 모두 찾는다 -> 30만 제한은 불가한 풀이, 3천 제한은 가능
//    //정렬한 스코빌 지수 리스트에서 해당 최소 인덱스, 최대 인덱스를 사이에 둔 메뉴의 개수에 따라
//    //해당 최대, 최소 쌍에 대한 메뉴 조합이 정해진다.
//    //n개로 만들 수 있는 1개 메뉴 조합, 2개 메뉴 조합 + ... n개 메뉴 조합의 총 수 = (n-1)조합개수 * 2 + 1
//    static void solution(int left, int right) {
//        ans += ((scoville.get(right) - scoville.get(left)) * (combinationCount[right - left - 1] + 1)) % division;
//    }


    //쌍을 굳이 만들지 않고 n이최대값이 될 수 있는 경우의 수, 최솟값이 될 수 있는 경우의 수에 대한 계산을 한다.
    static void solution(int index) {
        if(index!=0){
            ans=ans.add(BigInteger.valueOf(combinationCount[index]*scoville.get(index))).add(BigInteger.valueOf(division));
        }
        if(index!=N-1){
            ans=ans.subtract(BigInteger.valueOf(combinationCount[N-index-1]*scoville.get(index))).mod(BigInteger.valueOf(division));
        }
        ans=ans.mod(BigInteger.valueOf(division));
    }

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

        //50점 풀이
//        for (int i = 0; i < N; i++) {
//            for (int j = i + 1; j < N; j++) {
//                solution(i, j);
//            }
//        }

        for (int i = 0; i < N; i++) {
            solution(i);
        }
        System.out.println(ans.mod(BigInteger.valueOf(division)));
    }
}
