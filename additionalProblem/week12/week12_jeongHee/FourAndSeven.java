package additional;

import java.util.ArrayList;
import java.util.Scanner;

public class FourAndSeven {
    //https://www.acmicpc.net/problem/2877
    //4와 7

    //dp 풀이 실패
//    public static int getKthNumber(int K) {
//        int count = 2;
//        ArrayList<ArrayList<Integer>> dp = new ArrayList<>();
//        dp.add(new ArrayList<>());
//        dp.get(0).add(4);
//        if (K == 1) return 4;
//        dp.get(0).add(7);
//        if (K == 2) return 7;
//
//        int level = 0;
//        ArrayList<Integer> arr = dp.get(level);
//        while (true) {
//            dp.add(new ArrayList<>());
//            for (int i = 0; i <arr.size(); i++) {
//                count++;
//                int num = arr.get(i) * 10 + 4;
//                dp.get(level).add(num);
//                if (count == K) return num;
//
//                count++;
//                num = arr.get(i) * 10 + 7;
//                dp.get(level).add(num);
//                if (count == K) return num;
//            }
//            arr = dp.get(level+1);
//            dp.remove(level);
//        }
//
//    }

    public static String getKthNumber(int K) {
        StringBuilder sb =new StringBuilder();
        int tmpK = K;
        int base = 1;
        while (tmpK != 1) {
            tmpK /= 2;
            base *= 2;
            sb.append("4");
        }
        base--;
        for (int i = base; i < K - 1; i++) {
            int index = sb.length()-1;
            while (sb.charAt(index)!='4'){
                sb.setCharAt(index,'4');
                index--;
            }
            sb.setCharAt(index,'7');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        System.out.println(getKthNumber(K + 1));
    }
}
