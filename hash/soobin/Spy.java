package soobin;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Spy {

    public static int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        AtomicInteger answer = new AtomicInteger(1);

        for(String[] cloth : clothes) map.put(cloth[1], map.getOrDefault(cloth[1], 1) + 1);
        map.values().forEach((value) -> answer.updateAndGet(v -> v * value));
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
