package queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SpinningQueue {
    //https://www.acmicpc.net/problem/1021
    //회전하는 큐
    public static int searchNumber(Deque<Integer> queue, int number, int count){
        if(!queue.isEmpty()&&queue.peekFirst()==number)
            return count;
        int peek = queue.pollFirst();
        int answer = searchNumber(queue,number,count+1);
        queue.addFirst(peek);
        return answer;
    }
    public static void main(String[] args) {
        Deque<Integer> queue = new ArrayDeque<>();
        Scanner sc = new Scanner(System.in);
        int answer = 0;
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            queue.addLast(i+1);
        }
        int m = sc.nextInt();
        for (int i = 0; i <m ; i++) {
            int number = sc.nextInt();
            int leftToRight = SpinningQueue.searchNumber(queue,number,0);
            if(leftToRight>=queue.size()-leftToRight){
                while (queue.peekFirst()!=number){
                    queue.addFirst(queue.pollLast());
                    answer++;
                }
            }
            else {
                while (queue.peekFirst()!=number){
                    queue.addLast(queue.pollFirst());
                    answer++;
                }
            }
            queue.pollFirst();
        }
        System.out.println(answer);
    }
}
