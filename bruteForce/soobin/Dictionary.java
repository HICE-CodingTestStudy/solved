package soobin;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private static final char[] VOWELS = {'A', 'E', 'I', 'O', 'U'};
    private static final List<String> DICTIONARY = new ArrayList<>();
    private static int currentVowel = 0;
    // 현재 이어 붙일 모음 인덱스

    private static void generate(StringBuilder sb, int length) {
        if(sb.length() == length) {
//            DICTIONARY.add(sb.toString());
            sb.delete(sb.length() - 1, sb.length());
            currentVowel = currentVowel == 4 ? 0 : currentVowel + 1;
            return;
        }

        for (int i = 1; i <= 5; i++) {
            sb.append(VOWELS[currentVowel]);
            generate(sb, i);
            DICTIONARY.add(sb.toString());
//            sb.delete(sb.length() - 1, sb.length());
        }
    }

    public static int solution(String word) {
        int answer = 0;
        generate(new StringBuilder(), 1);
        System.out.println(DICTIONARY);
        return answer;
    }

    public static void main(String[] args) {
        String word = "AAAAE";
        System.out.println(solution(word));
    }
}
