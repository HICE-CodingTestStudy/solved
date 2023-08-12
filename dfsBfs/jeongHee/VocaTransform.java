package algoStudy.week1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class VocaTransform {
    //https://school.programmers.co.kr/learn/courses/30/lessons/43163
    //단어 변환
    public int solution(String begin, String target, String[] words) {
        Queue <String> queue = new LinkedList<>();
        queue.add(begin);
        HashMap<String,Integer> visited = new HashMap<>();
        HashMap<String,Integer> distance = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            visited.put(words[i],0);
        }
        distance.put(begin,0);
        while (!queue.isEmpty()){
            String nextNode = queue.poll();
            if(nextNode.equals(target)) return distance.get(nextNode);
            for (int i = 0; i < words.length; i++) {
                int diff = 0;
                int index = -1;
                while (index<words[i].length()-1){
                    index++;
                    if(nextNode.charAt(index)==words[i].charAt(index))
                        continue;
                    diff++;
                    if(diff>1) break;
                }
                if(diff!=1) continue;
                if(visited.get(words[i])==1) continue;
                queue.add(words[i]);
                visited.put(words[i],1);
                distance.put(words[i],distance.get(nextNode)+1);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        new VocaTransform().solution("hit","cog",new String[]{"hot","dot","dog","lot","log","cog"});
    }

}
