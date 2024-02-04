import java.io.*;

public class TreeOrderV2 {
    private static class Index {
        int inOrder, postOrder;

        public Index(int inOrder, int postOrder) {
            this.inOrder = inOrder;
            this.postOrder = postOrder;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] inOrder, inOrderIndexes, postOrder;
    private static boolean[] isPostOrderUsed;
    private static int N;

    public static void main(String[] args) throws IOException {
        parseInput();
        Index root = initRoot();
        printPreOrder(root, 0,  root.postOrder);
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
        inOrderIndexes = new int[N + 1];
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(inOrderInput[i]);
            inOrder[i] = value;
            inOrderIndexes[value] = i;
        }
    }

    private static void parsePostOrder() throws IOException {
        String[] postOrderInput = br.readLine().split(" ");

        postOrder = new int[N];
        isPostOrderUsed = new boolean[N];
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(postOrderInput[i]);
            postOrder[i] = value;
        }
    }

    // 포스트오더의 가장 오른쪽 끝 노드는 전체 트리의 Root
    private static Index initRoot() {
        int rootValue = postOrder[N - 1];
        isPostOrderUsed[N - 1] = true;
        return new Index(inOrderIndexes[rootValue], N - 1);
    }

    /*
    * 재귀를 통해 현재 서브 트리에서 자식 서브 트리로 들어가는 방식
    * 프리오더는 "VLR"이므로, 왼쪽 자식 서브 트리부터 탐색해야 함
     */
    private static void printPreOrder(
            Index root, int startOfInOrderRange, int endOfInOrderRange
    ) {
        appendBuffer(inOrder[root.inOrder]);
        if (startOfInOrderRange == endOfInOrderRange) return;

        int postLeftRootIdx = getLeftSubRootIdxOfPostOrder(root, endOfInOrderRange);
        if (isValidSubTree(postLeftRootIdx)) {
            Index leftSubTree = getSubTreeRoot(postLeftRootIdx);
            printPreOrder(leftSubTree, startOfInOrderRange, root.inOrder - 1);
        }

        int postRightRootIdx = getRightSubRootIdxOfPostOrder(root.postOrder);
        if (isValidSubTree(postRightRootIdx)) {
            Index rightSubTree = getSubTreeRoot(postRightRootIdx);
            printPreOrder(rightSubTree, root.inOrder + 1, endOfInOrderRange);
        }
    }

    private static void appendBuffer(int value) {
        try {
            bw.write(value + " ");
        } catch (IOException ignored) {}
    }

    /* 현재 서브 트리의 루트에서 왼쪽 자식의 포스트오더 위치를 찾으려면
    * 루트의 포스트오더 위치에서 (오른쪽 서브 트리의 노드 수 + 1)을 빼주면 됨
    *
    * Ex. 현재 서브 트리의 루트 = 1, 포스트오더 위치 = 8
    * Index :       0 1 2 3 4   5   6 7 8
    * In Order :    4 9 2 8 5 | 1 | 6 3 7
    * Post Order :  9 4 8 5 2 | 6 7 3 | 1
    * 왼쪽 서브 트리의 루트 = 2, 포스트오더 위치 = 8 - (8 - 5) - 1 = 4
     */
    private static int getLeftSubRootIdxOfPostOrder(Index root, int endOfInOrderRange) {
        return root.postOrder - (endOfInOrderRange - root.inOrder) - 1;
    }

    private static int getRightSubRootIdxOfPostOrder(int postOrderRootIdx) {
        return postOrderRootIdx - 1;
    }

    private static boolean isValidSubTree(int subRootIdx) {
        return subRootIdx >= 0 && subRootIdx < N && !isPostOrderUsed[subRootIdx];
    }

    private static Index getSubTreeRoot(int postIdx) {
        isPostOrderUsed[postIdx] = true;
        int leftSubRootIdx = inOrderIndexes[postOrder[postIdx]];
        return new Index(leftSubRootIdx, postIdx);
    }
}
