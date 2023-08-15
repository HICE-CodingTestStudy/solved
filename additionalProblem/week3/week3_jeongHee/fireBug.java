package queue;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class fireBug {
    //https://www.acmicpc.net/problem/3020
    //개똥벌레
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int H = sc.nextInt();
        int[] breaks = new int[H+1];
        breaks[0]=Integer.MAX_VALUE;
        PriorityQueue<Integer> down = new PriorityQueue<>();
        PriorityQueue<Integer> up = new PriorityQueue<>();
        for (int i = 0; i < N/2; i++) {
            down.add(sc.nextInt());
            up.add(sc.nextInt());
        }
        int shortestDown = down.peek();
        boolean isFinished = false;
        for (int i = 1; i < H+1 ; i++) {
            if(shortestDown==i&&!isFinished){
                breaks[i]=down.size();
                if(down.size()==1 && !isFinished){
                    isFinished=true;
                    down.poll();
                    continue;
                }
                down.poll();
                if(shortestDown==down.peek()){
                    while (!down.isEmpty()&&down.peek()==shortestDown){
                        down.poll();
                    }
                    if(down.isEmpty())
                        break;
                }
                shortestDown = down.peek();
                continue;
            }
            breaks[i]=down.size();
        }
        int shortestUp = up.peek();
        isFinished = false;
        for (int i = H; i >= 1 ; i--) {
            if(shortestUp+i==H+1&&!isFinished){
                breaks[i]+=up.size();
                if(up.size()==1 && !isFinished){
                    isFinished=true;
                    up.poll();
                    continue;
                }
                up.poll();
                if(shortestUp==up.peek()){
                    while (!up.isEmpty()&&up.peek()==shortestUp){
                        up.poll();
                    }
                    if(up.isEmpty())
                        break;
                }
                shortestUp = up.peek();
                continue;
            }
            breaks[i]+=up.size();
        }
        Arrays.sort(breaks);
        int wall = breaks[0];
        int count = 0;
        for (int i = 0; i < breaks.length; i++) {
            if(wall==breaks[i]) count++;
            else break;
        }
        System.out.print(wall + " " + count);
    }
}
