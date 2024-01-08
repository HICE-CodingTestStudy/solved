package queue;

public class LockAndKey {
    //https://school.programmers.co.kr/learn/courses/30/lessons/60059
    //자물쇠와 열쇠
    static int iLength = 0;
    static int jLength = 0;

    public int[][] rotate(int[][] arr) {
        int N = arr.length;
        int[][] ret = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ret[i][j] = arr[N - 1 - j][i];
            }
        }
        return ret;
    }

    public boolean search(int[][] key, int[][] lock, int iStart, int jStart) {
        if(iLength==0&&jLength==0) return true;
        if(key.length<iLength || key.length<jLength) return false;
        for (int i = 0; i < key.length; i++) {
            if (i + iLength > key.length) continue;
            for (int j = 0; j < key.length; j++) {
                if (j + jLength > key.length) continue;
                boolean isTrue = true;
                //돌린 키의 특정 부분
                int tmpIStart = iStart;
                for (int k = i; k < i + iLength; k++, tmpIStart++) {
                    if(!isTrue) break;
                    int tmpJStart = jStart;
                    for (int l = j; l < j + jLength; l++, tmpJStart++) {
                        if (key[k][l] == lock[tmpIStart][tmpJStart]) {
                            isTrue = false;
                            break;
                        }
                    }
                }
                if(isTrue) return true;
            }
        }
        return false;
    }

    public boolean solution(int[][] key, int[][] lock) {
        int iMin = Integer.MAX_VALUE;
        int jMin = Integer.MAX_VALUE;
        int iMax = Integer.MIN_VALUE;
        int jMax = Integer.MIN_VALUE;
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock[i].length; j++) {
                if (lock[i][j] == 0) {
                    iMin = Math.min(iMin, i);
                    jMin = Math.min(jMin, j);
                    iMax = Math.max(iMax, i);
                    jMax = Math.max(jMax, j);
                }
            }
        }
        iLength = iMax - iMin + 1;
        jLength = jMax - jMin + 1;
        if(iMin == Integer.MAX_VALUE || jMin == Integer.MAX_VALUE || iMax == Integer.MIN_VALUE || jMax == Integer.MIN_VALUE){
            iLength = 0;
            jLength = 0;
        }
        int[][] nine = rotate(key);
        int[][] eight = rotate(nine);
        int[][] twoSeven = rotate(eight);
        return search(key, lock, iMin, jMin)
                || search(nine, lock, iMin, jMin)
                || search(eight, lock, iMin, jMin)
                || search(twoSeven, lock, iMin, jMin);
    }

    public static void main(String[] args) {
        LockAndKey l = new LockAndKey();
        boolean b = l.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
        System.out.println(b);
    }
}
