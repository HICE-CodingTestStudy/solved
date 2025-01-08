import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Card {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] origin, dest, shuffle;

    public static void main(String[] args) throws Exception {
        parseInput();
        System.out.println(solution());
    }

    private static void parseInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        origin = new int[N];
        dest = new int[N];
        shuffle = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            origin[i] = dest[i] = Integer.parseInt(st.nextToken());
            shuffle[i] = Integer.parseInt(st2.nextToken());
        }
    }

    private static int solution() {
        int count = 0;

        while (!isFinished(dest)) {
            dest = shuffle(dest);
            if (isOrigin(dest)) return -1;
            count++;
        }

        return count;
    }

    private static int[] shuffle(int[] cards) {
        int[] shuffled = new int[N];

        for (int i = 0; i < N; i++) {
            int to = shuffle[i];
            shuffled[to] = cards[i];
        }

        return shuffled;
    }

    private static boolean isOrigin(int[] cards) {
        for (int i = 0; i < N; i++)
            if (origin[i] != cards[i]) return false;
        return true;
    }

    private static boolean isFinished(int[] cards) {
        for (int i = 0; i < N; i++)
            if ((i % 3) != cards[i]) return false;
        return true;
    }
}