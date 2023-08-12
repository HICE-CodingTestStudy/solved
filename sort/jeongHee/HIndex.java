package sort;

import java.util.Arrays;
import java.util.Collections;

public class HIndex {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42747
    //H-Index

    public int solution(int[] citations) {
        Integer[] IntegerCitations = Arrays.stream(citations).boxed().toArray(Integer[]::new);

        Arrays.sort(IntegerCitations, Collections.reverseOrder());
        for (int i = IntegerCitations.length; i >=0; i--) {
            int count = 0;
            for (int j = 0; j < IntegerCitations.length; j++) {
                if(IntegerCitations[j]>=i)
                    count++;
            }
            if(count>=i)
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        HIndex hIndex = new HIndex();
        System.out.println(hIndex.solution(new int[]{88,99}));
    }
}
