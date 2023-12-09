package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Guitar {
    //https://www.acmicpc.net/problem/2343
    //기타 레슨
    static int N, M;
    static List<Long> lecture = new ArrayList<>();

    static long solution(long left, long right) {
        while (left <= right) {
            long sum = 0;
            int count = 0;
            long mid = (left + right) / 2;
            for (int i = 0; i < lecture.size(); i++) {
                if (sum + lecture.get(i) > mid) {
                    sum = 0;
                    count++;
                }
                sum += lecture.get(i);
            }
            if (sum != 0) count++;
            if (count <= M)
                right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        long sum = 0, min = 0;
        for (int i = 0; i < N; i++) {
            lecture.add(Long.parseLong(st.nextToken()));
            sum += lecture.get(i);
            min = Math.max(min, lecture.get(i));
        }
        System.out.println(solution(min, sum));
    }

}
