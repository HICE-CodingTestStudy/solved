import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Candidate {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final List<int[]> photoWall = new ArrayList<>();
    private static final boolean[] isInWall = new boolean[100];
    private static final int[] thumbsUp = new int[100];

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int student = Integer.parseInt(st.nextToken()) - 1;
            thumbsUp[student]++;
            if (isInWall[student]) continue;

            isInWall[student] = true;
            if (photoWall.size() < n) {
                photoWall.add(new int[] {i, student});
                continue;
            }

            sort();
            int[] toRemove = photoWall.remove(0);
            isInWall[toRemove[1]] = false;
            photoWall.add(new int[] {i, student});
            thumbsUp[toRemove[1]] = 0;
        }

        photoWall.sort(Comparator.comparingInt(o -> o[1]));
        StringBuilder sb = new StringBuilder();
        for (int[] s : photoWall) {
            sb.append(s[1]+1).append(" ");
        }
        System.out.println(sb);
    }

    private static void sort() {
        photoWall.sort((o1, o2) -> {
            if (thumbsUp[o1[1]] == thumbsUp[o2[1]]) return o1[0] - o2[0];
            return thumbsUp[o1[1]] - thumbsUp[o2[1]];
        });
    }
}