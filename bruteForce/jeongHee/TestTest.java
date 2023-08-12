package bruteFroce;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class TestTest {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42840
    //모의고사
    public int[] solution(int[] answers) {
        int[] answer = new int[3];
        int[] firstPattern = {1,2,3,4,5};
        int[] secondPattern = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] thirdPattern = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int pointer = 0;
        while (pointer<answers.length){
            if(firstPattern[pointer%5]==answers[pointer])
                answer[0]++;
            if(secondPattern[pointer%8]==answers[pointer])
                answer[1]++;
            if(thirdPattern[pointer%10]==answers[pointer])
                answer[2]++;
            pointer++;
        }
        int max = -1;
        for(int i=0;i<answer.length;i++){
            if(max<=answer[i])
                max=answer[i];
        }
        List<Integer> ansList = new ArrayList<>();
        for(int i=0;i<answer.length;i++){
            if(max==answer[i]){
                ansList.add(i+1);
            }
        }
        int[] ansPrint = new int[ansList.size()];
        for (int i = 0 ; i < ansList.size() ; i++) {
            ansPrint[i] = ansList.get(i);
        }
        return ansPrint;
    }
}
