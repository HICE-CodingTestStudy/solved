package soobin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tree2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer pre;

    private static void post(List<String> split, String root) throws IOException {
        int id = split.indexOf(root);
        List<String> L = split.subList(0, id);
        List<String> R = split.subList(id + 1, split.size());

        // Leaf
        if (L.size() == 0 && R.size() == 0) {
            bw.write(root + " ");
            return;
        }

        if (L.size() > 0) post(L, pre.nextToken());
        if (R.size() > 0) post(R, pre.nextToken());
        // Subtree Root
        bw.write(root + " ");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            pre = new StringTokenizer(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            List<String> in = new ArrayList<>();
            while (N-- > 0) in.add(st.nextToken());

            post(in, pre.nextToken());
            bw.newLine();
        }
        bw.flush();
    }
}
