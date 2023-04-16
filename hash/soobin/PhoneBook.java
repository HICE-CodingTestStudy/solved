package soobin;

import java.util.*;

public class PhoneBook {
    public static boolean solution(String[] phone_book) {
        // key 값으로 정렬해서 저장되는 Set
        TreeSet<String> phoneBook = new TreeSet<>(Arrays.asList(phone_book));
        String prev = " ";
        // 어차피 정렬되어 있으므로 이전 전화번호가 다음 전화번호의 접두사인지만 확인
        for(String number : phoneBook) {
            if(number.startsWith(prev)) return false;
            prev = number;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};
        System.out.println(solution(phone_book));
    }
}
