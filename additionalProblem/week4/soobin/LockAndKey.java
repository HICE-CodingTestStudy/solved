package week4.soobin;

import java.util.*;

public class LockAndKey {
    private final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private class Coordinate {
        int r, c;

        Coordinate(int r, int c) { this.r = r; this.c = c;}

        @Override
        public boolean equals(Object obj) {
            return this.r == ((Coordinate) obj).r && this.c == ((Coordinate) obj).c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    private Set<Coordinate> locks;
    private List<Coordinate> keys;

    private List<Coordinate> rotate(List<Coordinate> original, int n) {
        List<Coordinate> rotated = new ArrayList<>();

        for (Coordinate key : original) {
            int r = n - 1 - key.r, c = key.c;
            rotated.add(new Coordinate(c, r));
        }

        return rotated;
    }

    private boolean findKey(int n) {
        List<Coordinate> keys = this.keys;
        for (int i = 0; i < 4; i++) {
            keys = rotate(keys, n);

            for (int[] move : moves){
                for (int j = 0; j < n; j++) {
                    int count = 0;
                    for (Coordinate key : keys) {
                        int r = key.r + move[0] * j;
                        int c = key.c + move[1] * j;
                        if (r < 0 || r >= n || c < 0 || c >= n) continue;

                        if (locks.contains(new Coordinate(r, c)))
                            count++;
                    }
                    if (count == locks.size())
                        return true;
                }
            }
        }
        return false;
    }

    public boolean solution(int[][] key, int[][] lock) {
        int nk = key.length, nl = lock.length;
        keys = new ArrayList<>();
        locks = new HashSet<>();

        for (int i = 0; i < nk; i++)
            for (int j = 0; j < nk; j++)
                if (key[i][j] == 1) keys.add(new Coordinate(i, j));

        for (int i = 0; i < nl; i++)
            for (int j = 0; j < nl; j++)
                if (lock[i][j] == 0) locks.add(new Coordinate(i, j));

        return findKey(nk);
    }

    public static void main(String[] args) {
        LockAndKey l = new LockAndKey();
        int[][] key = {{0,0,0}, {1,0,0}, {0,1,1}};
        int[][] lock = {{1,1,1}, {1,1,0}, {1,0,1}};
        System.out.println(l.solution(key, lock));
    }
}
