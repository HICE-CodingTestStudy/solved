package algorithmInClass.hash;

import java.util.HashMap;

//폰켓몬
//https://school.programmers.co.kr/learn/courses/30/lessons/1845
public class Programmers1845 {
    public int solution(int[] nums) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hm.put(nums[i],1);
        }
        return Math.min(hm.size(), (nums.length + 1) / 2);
    }
}
