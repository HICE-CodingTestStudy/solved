package additional;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ICanNotDoThisAllDay {
    //https://www.acmicpc.net/problem/7983
    //내일 할거야
    public static int maxT = 0;
    public static int ans;
    public static class HW implements Comparable<HW> {
        int work;
        int due;

        public HW(int work, int due) {
            this.work = work;
            this.due = due;
        }

        @Override
        public int compareTo(HW o) {
            return due-o.due;
        }
    }

    public static void main(String[] args) {
        //듀에 걸리지 않는 한에서 지금까지 과제를 했던 날 뺴고 최대한 미뤄서 과제를 끝낸다
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<HW> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int d = sc.nextInt();
            int t = sc.nextInt();
            maxT = Math.max(maxT,t);
            pq.add(new HW(d,t));
        }
        ans = maxT;
        //a가 마감일인 과제는 언제 시작해야하는지에 대한 날짜가 담긴 hashMap
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 1; i <= maxT; i++) {
            hashMap.put(i,i);
        }
        while (!pq.isEmpty()){
            HW hw = pq.poll();
            while(true){
                int day = hashMap.get(hw.due);
//                if(day==hw.due){
//                    hashMap.d
//                }
            }
        }


    }

}
