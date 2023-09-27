package queue;

import java.util.ArrayList;
import java.util.HashMap;

public class Tuple {
    //https://school.programmers.co.kr/learn/courses/30/lessons/64065
    //튜플
    public ArrayList<Integer> solution(String s) {
        ArrayList<Integer> answer = new ArrayList<>();
        String set = s.substring(2,s.length()-2);
        String[] sets = set.split("\\},\\{");
        HashMap<Integer,ArrayList<String>> hashMap = new HashMap<>();
        for (int i = 0; i < sets.length; i++) {
            String[] numbers = sets[i].split(",");
            ArrayList<String> arrayList = new ArrayList<>();
            for (int j = 0; j < numbers.length; j++) {
                arrayList.add(numbers[j]);
            }
            hashMap.put(numbers.length,arrayList);
        }
        for (int i = 0; i < hashMap.size(); i++) {
            int next = Integer.parseInt(hashMap.get(i+1).get(0));
            answer.add(next);
            for (int j = i; j < hashMap.size(); j++) {
                hashMap.get(j+1).remove(Integer.toString(next));
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Tuple tuple = new Tuple();
        tuple.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
    }
}
