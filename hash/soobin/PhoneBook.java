package soobin;

import java.util.*;

public class PhoneBook {
    public static boolean solution(String[] phone_book) {
        TreeSet<String> phoneBook = new TreeSet<>(Arrays.asList(phone_book));
        String prev = " ";
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
