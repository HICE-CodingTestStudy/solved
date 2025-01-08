import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Dasom {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[] woods;
    private static int N, C, W;
    private static int minLength, maxMoney;

    public static void main(String[] args) throws Exception {
        parseInput();
        for (int unit = 1; unit <= minLength; unit++) {
            cutWood(0, unit, 0, 0);
        }
        System.out.println(maxMoney);
    }

    private static void parseInput() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        woods = new int[N];

        minLength = 100000;
        for (int i = 0; i < N; i++) {
            woods[i] = Integer.parseInt(br.readLine());
            minLength = Math.min(minLength, woods[i]);
        }
    }

    private static void cutWood(int idx, int unit, int woodPile, int cut) {
        if (idx == N) {
            int money = (woodPile * unit * W) - (cut * C);
            maxMoney = Math.max(maxMoney, money);
            return;
        }

        int mod = woods[idx] / unit;
        for (int i = 0; i <= mod; i++) {
            if (i == mod) {
                int nextCut = woods[idx] % unit > 0 ? i : i - 1;
                cutWood(idx + 1, unit, woodPile + i, cut + nextCut);
            } else
                cutWood(idx + 1, unit, woodPile + i, cut + i);
        }
    }

    private static int sellWoods(int unit) {
        int count = 0, cut = 0;
        for (int wood : woods) {
            int mod = wood / unit;
            count += mod;
            cut += wood % unit > 0 ? mod : mod - 1;
        }

        return (count * unit * W) - (cut * C);
    }
}