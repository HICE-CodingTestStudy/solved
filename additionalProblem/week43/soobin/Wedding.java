import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Wedding {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] friends;

    public static void main(String[] args) {
        parseInput();
        int answer = inviteFriends();
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            friends = new List[N + 1];

            for (int i = 1; i <= N; i++)
                friends[i] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                String[] input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
                friends[a].add(b);
                friends[b].add(a);
            }
        } catch (IOException ignored) {}
    }

    private static int inviteFriends() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[friends.length];
        int numInvitedFriends = 0;
        visited[1] = true;
        queue.add(new int[] {1, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[1] == 2) continue;

            for (int friend : friends[current[0]]) {
                if (visited[friend]) continue;
                visited[friend] = true;
                queue.add(new int[] {friend, current[1] + 1});
                numInvitedFriends++;
            }
        }

        return numInvitedFriends;
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
