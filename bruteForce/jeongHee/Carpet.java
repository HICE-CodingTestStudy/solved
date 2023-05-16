package bruteFroce;

import java.util.ArrayList;

public class Carpet {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42842
    //카펫

    public ArrayList<Integer> solution(int brown, int yellow) {
        for (int i = 1; i <= yellow/2||i==1; i++) {
            if(yellow%i==0){
                int width = i;
                int height = yellow/width;
                if((width+2+height+2)*2-4==brown){
                    ArrayList<Integer> ans = new ArrayList<>();
                    ans.add(height+2);
                    ans.add(width+2);
                    return ans;
                }
            }
        }
        return new ArrayList<>();
    }
}
