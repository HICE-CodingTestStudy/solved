package Tree;

import java.util.HashMap;
import java.util.Scanner;

public class TreeCircular {
    //https://www.acmicpc.net/problem/1991
    //트리 순회
    public static class Sibling{
        public Sibling(char left, char right) {
            this.left = left;
            this.right = right;
        }

        char left;
        char right;
    }

    public static void preOrder(char node, HashMap<Character,Sibling> hm){
        if(node=='.') return;
        System.out.print(node);
        preOrder(hm.get(node).left,hm);
        preOrder(hm.get(node).right,hm);
    }

    public static void inOrder(char node, HashMap<Character,Sibling> hm){
        if(node=='.') return;
        inOrder(hm.get(node).left,hm);
        System.out.print(node);
        inOrder(hm.get(node).right,hm);
    }

    public static void postOrder(char node, HashMap<Character,Sibling> hm){
        if(node=='.') return;
        postOrder(hm.get(node).left,hm);
        postOrder(hm.get(node).right,hm);
        System.out.print(node);
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        HashMap<Character,Sibling> hm = new HashMap<>();
        for (int i = 0; i < N; i++) {
            char node = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);
            hm.put(node,new Sibling(left,right));
        }
        preOrder('A',hm);
        System.out.println();
        inOrder('A',hm);
        System.out.println();
        postOrder('A',hm);

    }
}
