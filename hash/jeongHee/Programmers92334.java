package algoStudy.week1;

import java.util.ArrayList;
import java.util.HashMap;

public class Programmers92334 {
    //신고 결과 받기 https://school.programmers.co.kr/learn/courses/30/lessons/92334
    public static int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, Integer> hm = new HashMap<>();
        //"신고한사람 신고받은사람" , 있는지 없는지(null, 1)
        HashMap<String ,Integer> hmCount = new HashMap<>();
        // 신고 받은 사람, 신고받은 횟수(중복제외)
        HashMap<String ,Integer> ans = new HashMap<>();
        // 신고한 사람- 신고 메일 받은 횟수
        for(int i=0;i<report.length;i++){
            //신고 저장
            String[] splitted = report[i].split(" ");
            if(hm.get(report[i])==null){
                //한 유저가 특정 유저 최초 신고시에만 신고 저장
                hm.put(report[i],1);
                if(hmCount.get(splitted[1])==null){
                    hmCount.put(splitted[1],1);
                    continue;
                }
                hmCount.put(splitted[1],hmCount.get(splitted[1])+1);
            }
        }
        for (String s : hmCount.keySet()) {
            if(hmCount.get(s)>=k){
                for(int i=0;i<id_list.length;i++){
                    String keyString = id_list[i]+" "+s;
                    if(hm.get(keyString)!=null){
                        if(ans.get(id_list[i])==null){
                            ans.put(id_list[i],1);
                        } else{
                            ans.put(id_list[i],ans.get(id_list[i])+1);

                        }
                    }
                }
            }
        }
        ArrayList<Integer> ansList = new ArrayList<>();
        for(int i=0;i<id_list.length;i++){
            if(ans.get(id_list[i])!=null)
                ansList.add(ans.get(id_list[i]));
            else{
                ansList.add(0);
            }
        }
        int[] ansArr = new int[ansList.size()];
        int size = 0;
        for(Integer integer:ansList){
            ansArr[size++] = integer;
        }
        return ansArr;


    }

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String [] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        solution(id_list,report,2);
    }

}
