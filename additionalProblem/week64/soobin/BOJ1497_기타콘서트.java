import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Concert {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static boolean[][] playable;
    private static int maxSong, minGuitar = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        parseInput();
        playGuitar(0, 0);
        System.out.println(maxSong == 0 ? -1 : minGuitar);
    }

    private static void parseInput() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        playable = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String songs = br.readLine().split(" ")[1];
            for (int j = 0; j < M; j++) {
                playable[i][j] = songs.charAt(j) == 'Y';
            }
        }
    }

    private static void playGuitar(int idx, int mask) {
        if (idx == N) {
            countPlayableSong(mask);
            return;
        }

        playGuitar(idx+1, mask + (1 << idx));
        playGuitar(idx+1, mask);
    }

    private static void countPlayableSong(int mask) {
        int numSongs = 0, numGuitar = 0;
        boolean[] songs = new boolean[M];

        for (int i = 0; i < N; i++) {
            if ((mask & (1 << i)) == 0) continue;
            numGuitar++;
            for (int j = 0; j < M; j++) {
                songs[j] |= playable[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            if (songs[i]) numSongs++;
        }

        if (numSongs >= maxSong) {
            maxSong = numSongs;
            minGuitar = Math.min(minGuitar, numGuitar);
        }
    }
}