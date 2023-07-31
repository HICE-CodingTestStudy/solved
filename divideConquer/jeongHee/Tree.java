package divideConquer;
import java.util.Scanner;

public class Tree {
    //https://www.acmicpc.net/problem/4256
    //트리
    public static void divide(int[] pre, int[] in, int pLeft, int pRight, int iLeft, int iRight){
        if(pLeft>pre.length-1||pLeft<0) return;
        int node = pre[pLeft];
        for (int i = iLeft,count = 0; i <= iRight; i++,count++) {
            if(in[i]==node){
                divide(pre,in,pLeft+1,pLeft+i,iLeft,i-1);
                divide(pre,in,pLeft+count+1,pRight,i+1,iRight);
                System.out.print(node+" ");
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[] pre = new int[n];
            int[] in = new int[n];
            for (int j = 0; j < n; j++) {
                pre[j]=sc.nextInt();
            }
            for (int j = 0; j < n; j++) {
                in[j]=sc.nextInt();
            }
            divide(pre,in,0,n-1,0,n-1);
            System.out.println();
        }
    }
}