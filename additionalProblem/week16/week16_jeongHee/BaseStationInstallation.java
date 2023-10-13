package com.example.demo.heap;

public class BaseStationInstallation {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12979
    //기지국 설치
    public int solution(int n, int[] stations, int w) {
        int start = 1;
        int end = start;
        int ans = 0;
        //기지국 사이사이의 칸들의 시작 범위, 끝 범위를 탐색한다.
        //해당 칸들이 현존 기지국으로 커버 되면 넘어간다.
        //아니면 커버 안된 칸수/한 기지국이 커버할수 있는 칸수 를 올림한 개수만큼 새롭게 기지국을 세운다.
        for (int i = 0; i < stations.length; i++) {
            if (start > n) break;
            end = stations[i] - w;
            int boundary = end - start;
            int plus = boundary % (2 * w + 1) == 0 ? boundary / (2 * w + 1) : boundary / (2 * w + 1) + 1;
            start = stations[i] + w + 1;
            if (boundary <= 0) continue;
            ans += plus;
        }
        //마지막 기지국을 탐색했으면 마지막 기지국 ~ 끝 단지 까지 똑같은 로직 수행
        if (start > n) return ans;
        end = n + 1;
        int boundary = end - start;
        if (boundary <= 0) return ans;
        ans += boundary % (2 * w + 1) == 0 ? boundary / (2 * w + 1) : boundary / (2 * w + 1) + 1;
        return ans;
    }

    public static void main(String[] args) {
        BaseStationInstallation b = new BaseStationInstallation();
        System.out.println(b.solution(13,new int[]{3,7,11},1));
        System.out.println(b.solution(5,new int[]{3},2));
        System.out.println(b.solution(6, new int[]{3}, 2));
    }
}
