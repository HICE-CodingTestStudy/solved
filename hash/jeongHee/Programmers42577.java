package algorithmInClass.hash;

import java.util.HashSet;

public class Programmers42577 {
    public boolean solution(String[] phone_book) {
        HashSet<String> hs = new HashSet<>();
        boolean answer = true;
        for (int i = 0; i < phone_book.length; i++) {
            hs.add(phone_book[i]);
        }
        //자바 10 이상 for문 간략화
        //for(var num : nums) hs.add(num);
        for(int i=0; i< phone_book.length;i++){
            for(int j=0;j<phone_book[i].length();j++){
                if(hs.contains(phone_book[i].substring(0, j))){
                    answer=false;
                }
            }
        }
        return answer;
    }
}
