package soobin;

import java.io.*;
import java.util.*;

public class RotatingQueue {
    public static int solution(int N, int M, int[] picks) {
        Deque<Integer> deque = new LinkedList<>();
        int answer = 0;
        int moved = 0;

        for(int i = 1; i <= N; i++) deque.addLast(i);
        for(int k = 0; k < M; k++) {
            int pick = picks[k];
            moved = 0;

            Iterator<Integer> iterator = deque.iterator();
            while(iterator.hasNext()) {
                if(iterator.next() == pick) break;
                moved++;
            }

            while(pick != deque.peekFirst()) {
                int tmp;
                if(moved < deque.size() - moved) {
                    tmp = deque.pollFirst();
                    deque.addLast(tmp);
                } else {
                    tmp = deque.pollLast();
                    deque.addFirst(tmp);
                }
                answer++;
            }

            deque.pollFirst();
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] picks = new int[st.countTokens()];
        for(int i = 0; st.hasMoreTokens(); i++) picks[i] = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(solution(N, M, picks)));
        bw.flush();
    }
}
