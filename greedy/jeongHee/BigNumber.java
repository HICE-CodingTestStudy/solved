package greedy;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BigNumber {

    //https://school.programmers.co.kr/learn/courses/30/lessons/42883
    //큰 수 만들기
    public String solution(String number, int k) {
        int originK=k;
        String sb = "";
        int maxInRangeK=-1;
        int maxIndexInRangeK=0;
        int first = maxIndexInRangeK;
        while(k!=0&&first<number.length()&&sb.length()<number.length()-originK) {
            for (int i = first; i<number.length()&&i < first+k+1; i++) {
                if (Integer.parseInt(String.valueOf(number.charAt(i))) > maxInRangeK) {
                    maxInRangeK = Integer.parseInt(String.valueOf(number.charAt(i)));
                    maxIndexInRangeK = i;
                    if(maxInRangeK==9)
                        break;
                }
            }
            sb+=number.charAt(maxIndexInRangeK);
            k=k-maxIndexInRangeK+first;
            first=maxIndexInRangeK+1;
            maxInRangeK=-1;
        }
        if(sb.length()!=number.length()-originK) {
            int more = number.length()-originK-sb.length();
            sb+=number.substring(number.length() - more);
        }
        return sb;
    }

    public static void main(String[] args) {
        BigNumber b = new BigNumber();
        System.out.println(b.solution("1231234",3));
    }
}
