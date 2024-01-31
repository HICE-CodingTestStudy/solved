import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TreeOrder {
    private static class Node {
        Node left, right;
        int value;

        public Node(int value) {
            this.value = value;
        }

        public boolean hasLeftSubTree() {
            return left != null;
        }

        public boolean hasRightSubTree() {
            return right != null;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Map<Integer, Integer> inOrderIndexes;
    private static Stack<Integer> postOrder;
    private static int[] inOrder;
    private static Node root;
    private static int N;

    public static void main(String[] args) throws IOException {
        parseInput();
        int rootIdx = initRoot();
        buildTree(rootIdx, 0, N - 1, root);
        printTree(root);
        bw.flush();
    }

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            parseInOrder();
            parsePostOrder();
        } catch (IOException ignored) {}
    }

    private static void parseInOrder() throws IOException {
        String[] inOrderInput = br.readLine().split(" ");

        inOrder = new int[N];
        inOrderIndexes = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(inOrderInput[i]);
            inOrder[i] = value;
            inOrderIndexes.put(value, i);
        }
    }

    private static void parsePostOrder() throws IOException {
        String[] postOrderInput = br.readLine().split(" ");

        postOrder = new Stack<>();
        for (String value : postOrderInput)
            postOrder.push(Integer.parseInt(value));
    }

    private static int initRoot() {
        int rootValue = postOrder.pop();
        root = new Node(rootValue);
        return  inOrderIndexes.get(rootValue);
    }

    private static void buildTree(int rootIdx, int leftIdx, int rightIdx, Node node) {
        if (leftIdx == rightIdx) return;

        if (isRightSubTreePossible(rootIdx))
            buildRightSubTree(rootIdx, rightIdx, node);

        if (isLeftSubTreePossible(rootIdx))
            buildLeftSubTree(rootIdx, leftIdx, node);
    }

    private static boolean isRightSubTreePossible(int rootIdx) {
        return !postOrder.isEmpty() && inOrderIndexes.get(postOrder.peek()) > rootIdx;
    }

    private static void buildRightSubTree(int rootIdx, int rightIdx, Node node) {
        int subTreeRootIdx = inOrderIndexes.get(postOrder.pop());
        node.right = new Node(inOrder[subTreeRootIdx]);
        buildTree(subTreeRootIdx, rootIdx + 1, rightIdx, node.right);
    }

    private static boolean isLeftSubTreePossible(int rootIdx) {
        return !postOrder.isEmpty() && inOrderIndexes.get(postOrder.peek()) < rootIdx;
    }

    private static void buildLeftSubTree(int rootIdx, int leftIdx, Node node) {
        int subTreeRootIdx = inOrderIndexes.get(postOrder.pop());
        node.left = new Node(inOrder[subTreeRootIdx]);
        buildTree(subTreeRootIdx, leftIdx, rootIdx - 1, node.left);
    }

    private static void printTree(Node root) {
        try {
            bw.write(root.value + " ");
            if (root.hasLeftSubTree()) printTree(root.left);
            if (root.hasRightSubTree()) printTree(root.right);
        } catch (IOException ignored) {}
    }
}
