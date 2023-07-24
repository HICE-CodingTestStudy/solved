package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Tree {
    //https://www.acmicpc.net/problem/1068
    //트리
    static int ans = 0;
    public static class Node{
        int node;
        ArrayList<Integer> siblings = new ArrayList<>();

        public Node(int node) {
            this.node = node;
        }
    }

    public static void circular(int node, Node[] nodes, int deleteNode){
        if(nodes[node].siblings.size()==0){
            ans++;
            return;
        }
        if(nodes[node].siblings.size()==1&&nodes[node].siblings.get(0)==deleteNode){
            ans++;
        }
        for (int sibling : nodes[node].siblings) {
            circular(sibling,nodes,deleteNode);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Node[] tree = new Node[N];
        for (int i = 0; i < N; i++) {
            tree[i]=new Node(i);
        }
        int startNode = -1;
        for (int i = 0; i < N; i++) {
            int nextInt = sc.nextInt();
            if(nextInt==-1) {
                startNode = i;
                continue;
            }
            tree[nextInt].siblings.add(i);
        }
        int deleteNode = sc.nextInt();
        tree[deleteNode].siblings=new ArrayList<>();
        circular(startNode,tree,deleteNode);
        System.out.println(ans-1);

    }
}
