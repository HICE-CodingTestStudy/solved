package divideConquer;

import java.util.Scanner;

public class ColorPaper {
    //https://www.acmicpc.net/problem/2630
    //색종이 만들기
    static int white = 0;
    static int blue = 0;
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
            if(now==1) blue++;
            else white++;
            return;
        }
        divide(x,y,n/2,paper);
        divide(x+n/2, y,n/2,paper);
        divide(x,y+n/2,n/2,paper);
        divide(x+n/2,y+n/2,n/2,paper);
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
        System.out.println(white);
        System.out.println(blue);
    }

}
