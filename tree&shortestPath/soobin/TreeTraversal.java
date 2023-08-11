package soobin;

import java.io.*;
import java.util.StringTokenizer;

public class TreeTraversal {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Node[] nodes;

    private static class Node {
        char id;
        Node left;
        Node right;

        public Node(char id) { this.id = id; }
    }

    private static void preorder(Node node) throws IOException {
        if (node == null) return;

        bw.write(String.valueOf(node.id));
        preorder(node.left);
        preorder(node.right);
    }

    private static void inorder(Node node) throws IOException {
        if (node == null) return;

        inorder(node.left);
        bw.write(String.valueOf(node.id));
        inorder(node.right);
    }

    private static void postorder(Node node) throws IOException {
        if (node == null) return;

        postorder(node.left);
        postorder(node.right);
        bw.write(String.valueOf(node.id));
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        for (int i = 0; i < N; i++) nodes[i] = new Node((char) (65 + i));

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String root = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            int rootId = root.charAt(0) - 65;
            if (!left.equals(".")) {
                int leftId = left.charAt(0) - 65;
                nodes[rootId].left = nodes[leftId];
            }
            if (!right.equals(".")) {
                int rightId = right.charAt(0) - 65;
                nodes[rootId].right = nodes[rightId];
            }
        }

        preorder(nodes[0]);
        bw.newLine();
        inorder(nodes[0]);
        bw.newLine();
        postorder(nodes[0]);
        bw.newLine();
        bw.flush();
    }
}
