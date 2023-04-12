package soobin;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Spy {

    public static int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        // 람다식은 final 변수만 사용 가능한데 final은 당연히 변수 값 변경이 안되니까
        // Atomic이라는 자바 문법을 사용 (원자 상태 보장)
        // 양심고백: Intellij 제안
        AtomicInteger answer = new AtomicInteger(1);

        // 옷 카테고리 별로 가능한 아이템 수 hash에 저장 (하나도 안 고르기 + 각 아이템 수)
        for(String[] cloth : clothes) map.put(cloth[1], map.getOrDefault(cloth[1], 1) + 1);
        // 카테고리 별 아이템 수 전체 곱하기 (전체 경우의 수)
        map.values().forEach((value) -> answer.updateAndGet(v -> v * value));
        // 하나도 안 입는 경우 빼서 리턴
        return answer.get() - 1;
    }

    public static void main(String[] args) {
        String[][] clothes = {
                {"yellow", "eyewear"},
                {"sunglasses", "eyewear"},
                {"tshirt", "top"},
                {"jean", "pants"},
                {"coat", "outer"}
        };
        System.out.println(solution(clothes));
    }
}
