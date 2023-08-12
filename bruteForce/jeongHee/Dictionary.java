package bruteFroce;

import java.util.HashSet;

public class Dictionary {
    //https://school.programmers.co.kr/learn/courses/30/lessons/84512
    //모음사전
    public int makeString(String[] alphabet, String voca, int[] count, String answer){
        if(voca.length()==alphabet.length){
            if(voca.equals(answer)){
                return count[0];
            }
            return -1;
        }

        for (int i = 0; i < alphabet.length ; i++) {
            if(voca.equals(answer)){
                return count[0];
            }
            count[0]++;
            int cnt = makeString(alphabet,voca+alphabet[i],count,answer);
            if(cnt!=-1) return cnt;
        }
        return -1;

    }

    public int solution(String word) {
        String[] alphabet = {"A","E","I","O","U"};
        int[] count ={0};
        return makeString(alphabet,"",count,word);

    }

    public static void main(String[] args) {
        Dictionary d = new Dictionary();
        d.solution("AAAAE");
    }
}
