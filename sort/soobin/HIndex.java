package soobin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HIndex {
    public static int solution(int[] citations) {
        List<Integer> citationList = new ArrayList<>();
        for (int citation : citations) citationList.add(citation);
        Collections.sort(citationList);

        int h = 1;
        for (;;h++) {
            int finalH = h;
            int over = (int) citationList.stream().filter(c -> c >= finalH).count();
            if(h > over) break;
        }

        return h - 1;
    }

    public static void main(String[] args) {
        int[] citations = {5,6,7};
        System.out.println(solution(citations));
    }
}
