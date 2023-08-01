package queue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixMultiply {
    //https://www.acmicpc.net/problem/10830
    //행렬 제곱
    public static BigInteger[][] solution(int[] binary, BigInteger[][] mat){
        BigInteger[][] ret = new BigInteger[mat.length][mat.length];
        boolean isChanged = false;
        for (int i = 0; i < binary.length; i++) {
            if(i==0&&binary[i]==1) {
                ret = mat;
                isChanged = true;
                mat = multiply(mat,mat);
                continue;
            }
            if(binary[i]==1){
                if(!isChanged){
                    ret = mat;
                    isChanged = true;
                }
                else ret = multiply(ret,mat);
            }
            mat = multiply(mat,mat);
        }
        return ret;
    }
    public static int[] binary(long num){
        int[] ret = new int[(int) (Math.log(num)/Math.log(2))+1];
        for (int i = ret.length-1; i >=0; i--) {
            long n = 1L <<i;
            if(num>=n){
                num-=n;
                ret[i]=1;
            }
        }
        return ret;
    }
    public static BigInteger[][] multiply(BigInteger[][] mat1, BigInteger[][] mat2){
        BigInteger[][] ret = new BigInteger[mat1.length][mat1.length];
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret.length; j++) {
                ret[i][j]=BigInteger.valueOf(0);
            }
        }
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1.length; j++) {
                for (int k = 0; k < mat1.length; k++) {
                    ret[i][j]=((mat1[i][k].multiply(mat2[k][j])).add(ret[i][j])).mod(BigInteger.valueOf(1000));
                }
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long B = sc.nextLong();
        BigInteger[][] mat = new BigInteger[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mat[i][j]= BigInteger.valueOf(sc.nextLong());
            }
        }
        BigInteger[][] result = solution(binary(B),mat);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j].mod(BigInteger.valueOf(1000))+ " ");
            }
            System.out.println();
        }
    }
}
