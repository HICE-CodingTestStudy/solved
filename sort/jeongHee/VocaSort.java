package sort;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class VocaSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<String> voca = new PriorityQueue<>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if(o1.length()!=o2.length())
                            return o1.length()-o2.length();
                        for (int i = 0; i < o1.length(); i++) {
                            if(o1.charAt(i)==o2.charAt(i))
                                continue;
                            return o1.charAt(i)-o2.charAt(i);
                        }
                        return 0;
                    }
                }
        );
        for (int i = 0; i < N; i++) {
            voca.add(sc.next());
        }
        while (!voca.isEmpty()){
            String thisVoca = voca.poll();
            while (!voca.isEmpty()&&thisVoca.equals(voca.peek())){
                voca.poll();
            }
            System.out.println(thisVoca);

        }
    }
}
