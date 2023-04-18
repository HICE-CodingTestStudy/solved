package stack;

public class GoToMars {
    //주식 가격
    //https://school.programmers.co.kr/learn/courses/30/lessons/42584
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for(int i=0;i<prices.length-1;i++){
            int time=0;
            for(int j=i+1;(j<prices.length);j++){
                time++;
                if(prices[j]<prices[i]){
                    break;
                }
            }
            answer[i]=time;
        }
        answer[prices.length-1]=0;
        return answer;
    }
}
