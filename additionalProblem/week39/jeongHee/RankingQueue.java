import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RankingQueue {
    //https://www.acmicpc.net/problem/20006
    //랭킹전 대기열
    static int p, m;
    static List<List<Player>> list = new ArrayList<>();

    static class Player implements Comparable<Player> {
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return name.compareTo(o.name);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            return sb.append(level).append(" ").append(name).append("\n").toString();
        }
    }

    static void solution(Player p) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() == m) continue;
            if (Math.abs(list.get(i).get(0).level - p.level) <= 10) {
                list.get(i).add(p);
                return;
            }
        }
        list.add(new ArrayList<>());
        list.get(list.size() - 1).add(p);
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() == m)
                sb.append("Started!").append("\n");
            else sb.append("Waiting!").append("\n");
            Collections.sort(list.get(i));
            for (Player player : list.get(i)) {
                sb.append(player.toString());
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(bf.readLine());
            solution(new Player(Integer.parseInt(st.nextToken()), st.nextToken()));
        }
        print();
    }
}
