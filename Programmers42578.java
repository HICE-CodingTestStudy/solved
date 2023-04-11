package algoStudy.week1;

import java.util.*;

public class Programmers42578 {
    //위장 https://school.programmers.co.kr/learn/courses/30/lessons/42578
    public static int solution(String[][] clothes) {
        HashMap<String,Integer> numOfClothes = new HashMap<>();
        for (String[] clothe : clothes) {
            String category = clothe[1];
            //카테고리 저장
            if(numOfClothes.get(category)==null){
                //카테고리 별로 갯수 저장
                numOfClothes.put(category,1);
                continue;
            }
            numOfClothes.put(category,numOfClothes.get(category)+1);
        }
        int answer=1;
        for (Integer i : numOfClothes.values()) {
            //전체 경우의수 = 각각의 경우의수 +1 을 다 곱한 뒤 1을 뺌(전부 안선택하는 경우)
            answer*=(i+1);
        }
        return answer-1;

    }

    public static void main(String[] args) {
        String[][] a = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(solution(a));
    }
}
