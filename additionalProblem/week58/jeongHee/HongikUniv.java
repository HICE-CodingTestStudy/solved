package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class HongikUniv {
    // https://www.acmicpc.net/problem/32345
    // 혼긱대학교
    static final int DIVIDE = 1_000_000_007;
    static Set<Character> vowel = new HashSet<>(Set.of('a', 'e', 'i', 'o', 'u'));
    public static void main(String[] args) throws IOException {
        /**
         * 자음들은 모음이 어떤 방식으로든 포함이 돼야 발음이 됨
         * 모음이 없는경우 -> 발음이 안됨
         * 모음이 하나인경우 -> 발음이 하나임
         * 모음이 N경우 -> 어느쪽 모음에 붙을것인가에 따라 다름 3 (1) 2 (1) -> 2,0 / 1,1 / 0,2
         * 사이에 낀 자음의수 +1 만큼의 경우 발생
         */
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String s = bf.readLine();
            long ans = -1;
            long tmpCnt = 0;
            for (int i = 0; i < s.length(); i++) {
                char now = s.charAt(i);
                if(vowel.contains(now)){
                    if(ans==-1){
                        tmpCnt = 0;
                        ans = 1;
                        continue;
                    }
                    ans *= (tmpCnt + 1) % DIVIDE;
                    ans %= DIVIDE;
                    tmpCnt = 0;
                    continue;
                }
                tmpCnt++;
            }
            sb.append(ans)
              .append("\n");
        }
        System.out.println(sb);
    }
}
