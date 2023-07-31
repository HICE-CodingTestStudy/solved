package queue;

import java.math.BigInteger;
import java.util.Scanner;

public class BoxFill {
    static int used = 0;
    public static void divide(int length, int width, int height, int[][] cubes, boolean[] isLeft){
        boolean canNotFill = true;
        if(length<=0||width<=0||height<=0) return;
        //해당 줄이 없으면 시간초과/메모리 초과
        int start = Math.min((int)(Math.log(Math.min(length,Math.min(height,width))) / Math.log(2)),cubes.length-1);
        for (int i = start; i >=0 ; i--) {
            if(!isLeft[i]) continue;
            canNotFill = false;
            int cube = 1<<cubes[i][0];
            if(length>=cube&&width>=cube&&height>=cube){
                canNotFill= false;
                int canFill = (length/cube)*(width/cube)*(height/cube);
                int realFill = Math.min(Math.min(canFill,cubes[i][1]),1);
                used+=realFill;
                cubes[i][1]-=realFill;
                if(cubes[i][1]==0) isLeft[i]=false;
                divide(length-cube,width, cube, cubes,isLeft);
                divide(cube,width-cube,cube,cubes,isLeft);
                divide(length,width,height-cube,cubes,isLeft);
                return;
            }
        }
        if(canNotFill)
            used = -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int width = sc.nextInt();
        int height = sc.nextInt();
        int n = sc.nextInt();
        int[][] cubes = new int[n][2];
        boolean[] isLeft = new boolean[n];
        for (int i = 0; i < n; i++) {
            cubes[i][0] = sc.nextInt();
            cubes[i][1] = sc.nextInt();
            if(cubes[i][1]!=0) isLeft[i] = true;
        }
        divide(length,width,height,cubes,isLeft);
        System.out.println(used);

    }
}
