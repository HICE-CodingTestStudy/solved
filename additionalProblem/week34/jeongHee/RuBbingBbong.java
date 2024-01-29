package stack;

import java.util.Scanner;

public class RuBbingBbong {
    //https://www.acmicpc.net/problem/20442
    //ㅋㅋ루ㅋㅋ
    static String string;
    static int countOfR = 0;
    static int ans = 0;

    static void countR() {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == 'R') countOfR++;
        }
    }

    static void solution() {
        countR();
        ans = countOfR;
        int left = 0;
        int right = string.length() - 1;
        int countOfK = 0;
        while (true) {
            ans = Math.max(ans, countOfK + countOfR);
            if(countOfR==0) return;
            if (left == right || left >= string.length() || right < 0) return;
            if (string.charAt(left) == 'K') {
                if (string.charAt(right) == 'K') {
                    countOfK += 2;
                    left++;
                    right--;
                    continue;
                }
                countOfR--;
                right--;
                continue;
            }
            if (string.charAt(right) == 'K') {
                countOfR--;
                left++;
                continue;
            }
            if (string.charAt(left) == 'R') {
                countOfR--;
                left++;
                continue;
            }
            if (string.charAt(right) == 'R') {
                countOfR--;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        string = sc.nextLine();
        solution();
        System.out.println(ans);
    }


}
