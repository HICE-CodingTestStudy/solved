package soobin;

public class Carpet {
    public static int[] solution(int brown, int yellow) {
        int[] answer = {0, 0};

        // width = (brown + 4 - height * 2) / 2
        for (int i = 3; (brown + 4 - i * 2) / 2 >= 3; i++) {
            int height = i;
            int width = (brown + 4 - height * 2) / 2;
            if (width * height - brown == yellow) {
                answer[0] = width;
                answer[1] = height;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int brown = 10;
        int yellow = 2;
        int[] answer = solution(brown, yellow);
        System.out.println(answer[0] + " " + answer[1]);
    }
}
