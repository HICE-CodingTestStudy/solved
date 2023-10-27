package additional2;

import java.util.ArrayList;
import java.util.Scanner;

public class Tetris {
    //https://www.acmicpc.net/problem/3019
    //테트리스
    static int C, P;
    static int[] map;
    static ArrayList<ArrayList<ArrayList<Integer>>> blocks = new ArrayList<>();

    //블록에 대해 붙어있는 칸들간의 높이차이를 구별할 수 있도록 숫자를 저장한다. 회전의 경우의 수 고려
    static void init() {
        //1번) 2종류
        for (int i = 0; i < 2; i++) {
            blocks.get(1).add(new ArrayList<>());
        }
        // 1자
        blocks.get(1).get(0).add(1);
        //눕힌것
        for (int i = 0; i < 4; i++) {
            blocks.get(1).get(1).add(1);
        }

        //2번 1종류
        blocks.get(2).add(new ArrayList<>());
        for (int i = 0; i < 2; i++) {
            blocks.get(2).get(0).add(1);
        }

        //3번 2종류
        for (int i = 0; i < 2; i++) {
            blocks.get(3).add(new ArrayList<>());
        }
        //눕힌것
        blocks.get(3).get(0).add(1);
        blocks.get(3).get(0).add(1);
        blocks.get(3).get(0).add(0);

        //세운것
        blocks.get(3).get(1).add(0);
        blocks.get(3).get(1).add(1);

        //4번 2종류
        for (int i = 0; i < 2; i++) {
            blocks.get(4).add(new ArrayList<>());
        }
        //눕힌것
        blocks.get(4).get(0).add(0);
        blocks.get(4).get(0).add(1);
        blocks.get(4).get(0).add(1);

        //세운것
        blocks.get(4).get(1).add(1);
        blocks.get(4).get(1).add(0);

        //5번 4종류
        for (int i = 0; i < 4; i++) {
            blocks.get(5).add(new ArrayList<>());
        }

        //ㅗ
        for (int i = 0; i < 3; i++) {
            blocks.get(5).get(0).add(1);
        }
        //ㅓ
        blocks.get(5).get(1).add(0);
        blocks.get(5).get(1).add(1);
        //ㅏ
        blocks.get(5).get(2).add(1);
        blocks.get(5).get(2).add(0);
        //ㅜ
        blocks.get(5).get(3).add(0);
        blocks.get(5).get(3).add(1);
        blocks.get(5).get(3).add(0);

        //6번 4종류
        for (int i = 0; i < 4; i++) {
            blocks.get(6).add(new ArrayList<>());
        }
        //__ㅣ
        for (int i = 0; i < 3; i++) {
            blocks.get(6).get(0).add(1);
        }
        //ㄴ
        for (int i = 0; i < 2; i++) {
            blocks.get(6).get(1).add(1);
        }
        //ㄱ
        blocks.get(6).get(2).add(0);
        blocks.get(6).get(2).add(2);
        //ㅣ--
        blocks.get(6).get(3).add(1);
        blocks.get(6).get(3).add(0);
        blocks.get(6).get(3).add(0);

        //7번 4종류
        for (int i = 0; i < 4; i++) {
            blocks.get(7).add(new ArrayList<>());
        }
        //ㅣ__
        for (int i = 0; i < 3; i++) {
            blocks.get(7).get(0).add(1);
        }
        //_ㅣ
        for (int i = 0; i < 2; i++) {
            blocks.get(7).get(1).add(1);
        }
        //ㅣ-
        blocks.get(7).get(2).add(2);
        blocks.get(7).get(2).add(0);
        //ㄱ
        blocks.get(7).get(3).add(0);
        blocks.get(7).get(3).add(0);
        blocks.get(7).get(3).add(1);

    }

    //블록을 내렸을때 인접한 칸들끼리의 높이차가 나지 않아야 한다 (높이 : 각 블록의 인덱스에 해당하는 수 + 기존에 깔려있던 수)
    static int solution() {
        int ans = 0;
        for (ArrayList<Integer> arrayList : blocks.get(P)) {
            for (int i = 0; i < C; i++) {
                if (arrayList.size() == 1) {
                    ans++;
                    continue;
                }
                int before = map[i] + arrayList.get(0);
                boolean isValid = true;
                for (int j = 1; j < arrayList.size(); j++) {
                    if (i + j >= C) {
                        isValid = false;
                        break;
                    }
                    int next = map[i + j] + arrayList.get(j);
                    if (next != before) {
                        isValid = false;
                        break;
                    }
                    before = next;
                }
                if (isValid) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt();
        P = sc.nextInt();
        map = new int[C];
        for (int i = 0; i < C; i++) {
            map[i] = sc.nextInt();
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new ArrayList<>());
        }
        init();
        System.out.println(solution());
    }

}
