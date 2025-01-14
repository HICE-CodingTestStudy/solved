import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Dasom {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[] woods;
    private static int N, C, W;
    private static int maxLength;

    public static void main(String[] args) throws Exception {
        parseInput();
        long answer = 0;
        for (int unit = 1; unit <= maxLength; unit++) {
            answer = Math.max(answer, sellWoods(unit));
        }
        System.out.println(answer);
    }

    private static void parseInput() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        woods = new int[N];

        maxLength = 100000;
        for (int i = 0; i < N; i++) {
            woods[i] = Integer.parseInt(br.readLine());
            maxLength = Math.max(maxLength, woods[i]);
        }
    }

    private static long sellWoods(int unit) {
        long money = 0;

        for (int wood : woods) {
            if (wood < unit) continue;

            int mod = wood / unit;
            int cut = wood % unit > 0 ? mod : mod - 1;
            money += Math.max(0, ((mod * unit * W) - (cut * C)));
        }

        return money;
    }
}