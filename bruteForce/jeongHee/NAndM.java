package bruteFroce;

import java.util.ArrayList;
import java.util.Scanner;

public class NAndM {
    //https://www.acmicpc.net/problem/15649
    //N과 M
    public static void checkPrime(int[] numbers, int M, int count, boolean[] used, ArrayList<Integer> selected){
        if(count==M){
            StringBuilder sb = new StringBuilder();
            for (int number : selected) {
                sb.append(number).append(" ");
            }
            System.out.println(sb);
            return;
        }
        for (int i = 0; i < numbers.length; i++) {
            if(!used[i]){
                used[i]=true;
                selected.add(numbers[i]);
                checkPrime(numbers,M,count+1,used,selected);
                selected.remove(selected.size()-1);
                used[i]=false;
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] numbers = new int[N];
        boolean[] used = new boolean[N];
        ArrayList<Integer> selected = new ArrayList<>();
        for (int i = 1; i <=N ; i++) {
            numbers[i-1]=i;
        }
        checkPrime(numbers,M,0,used,selected);
    }
}
