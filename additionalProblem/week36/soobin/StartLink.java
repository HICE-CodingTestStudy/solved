import java.io.*;

public class StartLink {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] abilities;
    private static int N, minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) {
        parseInput();
        makeTeam(0, 0, 0);
        printAnswer();
    }

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            abilities = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    int ability = Integer.parseInt(input[j]);
                    abilities[i][j] = ability;
                }
            }
        } catch (IOException ignored) {}
    }

    private static void makeTeam(int teamStart, int teamLink, int member) {
        if (member == N) {
            int teamStartAbility = calcTeamAbility(teamStart);
            int teamLinkAbility = calcTeamAbility(teamLink);
            minDiff = Math.min(Math.abs(teamStartAbility - teamLinkAbility), minDiff);
            return;
        }

        int teamStartWithMember = teamStart | (1 << member);
        int teamLinkWithMember = teamLink | (1 << member);

        makeTeam(teamStartWithMember, teamLink, member + 1);
        makeTeam(teamStart, teamLinkWithMember, member + 1);
    }

    private static int calcTeamAbility(int team) {
        int score = 0;

        for (int i = N - 1; i >= 0; i--) {
            if (isNotInTeam(team, i)) continue;

            for (int j = i - 1; j >= 0; j--) {
                if (isNotInTeam(team, j)) continue;

                score += abilities[i][j] + abilities[j][i];
            }
        }

        return score;
    }

    private static boolean isNotInTeam(int team, int idx) {
        return (team & (1 << idx)) == 0;
    }

    private static void printAnswer() {
        try {
            bw.write(String.valueOf(minDiff));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
