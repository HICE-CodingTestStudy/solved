package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HateCharge {
    //https://www.acmicpc.net/problem/20003
    //거스름돈이 싫어요
    static long aAns, bAns;

    static long multiple(long x, long y) {
        return x * y / divisor(x, y);
    }


    static long divisor(long num1, long num2) {
        if (num2 == 0) return num1;
        else return divisor(num2, num1 % num2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            long div = divisor(a, b);
            a /= div;
            b /= div;
            if (i == 0) {
                aAns = a;
                bAns = b;
                continue;
            }
            long tmpBAns = bAns;
            bAns = multiple(b, bAns);
            a = a * bAns / b;
            aAns = aAns * bAns / tmpBAns;
            aAns = divisor(aAns, a);
        }
        System.out.println(aAns + " " + bAns);
    }
}
