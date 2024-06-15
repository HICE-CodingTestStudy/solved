import java.io.*;

public class MapleStory {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] quests;
    private static int N, M;
    private static int answer = -1;

    public static void main(String[] args) throws Exception {
        parseInput();
        solution(0, 0, 0);
        printAnswer();
    }

    private static void parseInput() throws Exception {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);
        quests = new int[M];

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int mask = 0;
            for (int j = 0; j < k; j++) {
                int skill = Integer.parseInt(input[j]);
                mask += 1 << (skill - 1);
            }
            quests[i] = mask;
        }
    }

    private static void solution(int idx, int currentKeys, int numQuests) {
        if (idx == M) {
            answer = Math.max(answer, numQuests);
            return;
        }

        int newKeys = currentKeys | quests[idx];
        if (countOne(newKeys) <= N)
            solution(idx + 1, newKeys, numQuests + 1);
        solution(idx + 1, currentKeys, numQuests);
    }

    private static int countOne(int mask) {
        int count = 0;
        while (mask > 0) {
            mask &= mask - 1;
            count++;
        }
        return count;
    }

    private static void printAnswer() throws Exception {
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
