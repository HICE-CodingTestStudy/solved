package divideConquer;

import java.util.Scanner;

public class Z {
    //https://www.acmicpc.net/problem/1074
    //Z

    static int count = 0;
    //시간 초과 풀이
//    static boolean isEnd = false;
//    public static void divide(int x, int y, int n, int r, int c){
//        if(n==2){
//            for (int i = x; i <x+n ; i++) {
//                for (int j = y; j < y + n; j++) {
//                    if (i == r && j == c) {
//                        isEnd=true;
//                        return;
//                    }
//                    count++;
//                }
//            }
//            return;
//        }
//
//        if(!isEnd) divide(x,y,n/2,r,c);
//        if(!isEnd) divide(x,y+n/2,n/2,r,c);
//        if(!isEnd) divide(x+n/2, y,n/2,r,c);
//        if(!isEnd) divide(x+n/2,y+n/2,n/2,r,c);
//    }
    public static void divide(int x, int y, int n, int r, int c){
        if(x==r&&y==c) return;
        if(r>=x+n/2) {
            x =  x+n/2;
            count+=n*n/2;
        }
        if(c>=y+n/2) {
            y = y+n/2;
            count+=n*n/4;
        }
        divide(x,y,n/2,r,c);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        divide(0,0, (int) Math.pow(2,N),r,c);
        System.out.println(count);
    }

}
