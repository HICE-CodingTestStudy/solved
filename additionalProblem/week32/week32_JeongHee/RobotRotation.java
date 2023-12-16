package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class RobotRotation {
    //https://www.acmicpc.net/problem/20055
    //컨베이어 벨트 위의 로봇
    static int N, K;
    static int zero = 0;
    static int[] life;
    static boolean[] haveRobot;

    //현재까지 회전한 횟수
    //만약 현재까지 2회 회전했다면 현시점에서 0칸에 있는 컨베이어 밸트는 초기 시점 -2칸(N이 3일때 4칸)이다.
    static int rotateCount = 0;

    //현재 칸의 초기 시점 칸 인덱스를 리턴함.
    static int calcIndex(int index) {
        //초기 시점 칸의 인덱스 == index - rotateCount
        //초기 시점 칸의 인덱스를 계산했을때 회전 수가 많다면 큰 음수일 수 있으므로 이에 맞게 나머지 계산을 해준다.
        int ret = (index - rotateCount) % (2 * N) >= 0 ?
                (index - rotateCount) % (2 * N) : 2 * N + (index - rotateCount) % (2 * N);
        return ret;
    }

    static void put() {
        if (life[calcIndex(0)] != 0) {
            life[calcIndex(0)]--;
            if (life[calcIndex(0)] == 0) zero++;
            haveRobot[calcIndex(0)] = true;
        }
    }

    static void rotate() {
        haveRobot[calcIndex(N - 1)] = false;
        rotateCount++;
        haveRobot[calcIndex(N - 1)] = false;
    }

    static void move() {
        for (int i = N - 2; i >= 0; i--) {
            if (haveRobot[calcIndex(i)] && life[calcIndex(i + 1)] > 0 && !haveRobot[calcIndex(i + 1)]) {
                haveRobot[calcIndex(i)] = false;
                haveRobot[calcIndex(i + 1)] = true;
                life[calcIndex(i + 1)]--;
                if (life[calcIndex(i + 1)] == 0) zero++;
            }
        }
    }

    static int solution() {
        int ans = 1;
        while (true) {
            rotate();
            move();
            put();
            if (zero >= K) return ans;
            ans++;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        life = new int[2 * N];
        haveRobot = new boolean[2 * N];
        for (int i = 0; i < N * 2; i++) {
            life[i] = Integer.parseInt(st.nextToken());
            if (life[i] == 0) zero++;
        }
        System.out.println(solution());
    }
}
