package divideConquer;

import java.util.Scanner;

public class NumOfPaper {
    //https://www.acmicpc.net/problem/1780
    //종이의 개수

    static int minus = 0;

    static int plus = 0;
    static int zero = 0;
    public static void divide(int x, int y, int n, int[][] paper){
        int now = paper[x][y];
        boolean isSame = true;
        for (int i = x; i <x+n ; i++) {
            for (int j = y; j <y+n ; j++) {
                if(now!=paper[i][j]){
                    isSame=false;
                    break;
                }
            }
            if(!isSame) break;
        }
        if(isSame){
            if(now==-1) minus++;
            else if(now==0) zero++;
            else plus++;
            return;
        }
        divide(x,y,n/3,paper);
        divide(x+n/3, y,n/3,paper);
        divide(x+n/3*2, y,n/3,paper);
        divide(x,y+n/3,n/3,paper);
        divide(x+n/3,y+n/3,n/3,paper);
        divide(x+n/3*2,y+n/3,n/3,paper);
        divide(x,y+n/3*2,n/3,paper);
        divide(x+n/3,y+n/3*2,n/3,paper);
        divide(x+n/3*2,y+n/3*2,n/3,paper);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                paper[i][j]=sc.nextInt();
            }
        }
        divide(0,0,N,paper);
        System.out.println(minus);
        System.out.println(zero);
        System.out.println(plus);
    }

}
