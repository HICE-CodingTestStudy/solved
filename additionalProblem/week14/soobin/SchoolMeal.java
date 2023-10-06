package week14.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class SchoolMeal {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final double E = 1e6;

    private static int[] students, speeds;

    private static void input(int N) throws IOException {
        StringTokenizer student = new StringTokenizer(br.readLine());
        StringTokenizer speed = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(student.nextToken());
            speeds[i] = Integer.parseInt(speed.nextToken());
        }
    }

    private static int solution(int N, double T) {
        double left = Double.MIN_VALUE, right = Double.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            left = Math.max(left, students[i] - speeds[i] * T);
            right = Math.min(right, students[i] + speeds[i] * T);
        }

        return Math.round(left * E) / E <= Math.round(right * E) / E ? 1 : 0;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        double T = Double.parseDouble(st.nextToken());
        students = new int[N];
        speeds = new int[N];
        input(N);

        System.out.println(Math.round(6.3336 * E) / E <= Math.round(6.3332 * E) / E);
        bw.write(String.valueOf(solution(N, T)));
        bw.flush();
    }
}
