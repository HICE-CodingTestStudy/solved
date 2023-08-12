package algoStudy.week1;

import java.util.ArrayList;
import java.util.Scanner;

public class Spica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> stars = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            stars.add(new ArrayList<>());
        }
        for (int i = 0; i < 12; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            stars.get(a).add(b);
            stars.get(b).add(a);
        }
        for (int i = 1; i <= 12 ; i++) {
            if(stars.get(i).size()==3){
                int edge = 0;
                for (int j = 0; j < 3; j++) {
                    edge+=stars.get(stars.get(i).get(j)).size();
                }
                if(edge==6){
                    System.out.println(i);
                    return;
                }

            }
        }
    }
}
