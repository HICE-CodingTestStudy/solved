package fin.HICE.week55.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ_23031_Aplus {

    static boolean isValid(int y, int x, int n) {
        return (0<=x && x<n && 0<=y && y<n);
    }

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int n = Integer.parseInt(br.readLine());

         String move = br.readLine();
         boolean[][][] map = new boolean[3][n][n];
         ArrayDeque<int[]> zombies = new ArrayDeque<>();
         String tmp;
         for (int i = 0; i < n; i++) {
             tmp = br.readLine();
             for (int j = 0; j < n; j++) {
                 switch (tmp.charAt(j)) {
                     case 'Z':
                         zombies.add(new int[]{i, j, 0});
                         map[0][i][j] = true;
                         break;
                     case 'S':
                         map[1][i][j] = true;
                 }
             }
         }

         int[] curr = {0, 0}; int dir = 0;
         int zNum = zombies.size();

         int[] dy = {1, 0, -1, 0};
         int[] dx = {0, 1, 0, -1};

         int length = move.length();
         int[] currZ;
         for (int i = 0; i < length; i++) {
             // 아리가 움직임
             switch (move.charAt(i)) {
                 case 'F':
                     if(isValid(curr[0]+dy[dir], curr[1]+dx[dir], n)) {
                         curr[0] += dy[dir];
                         curr[1] += dx[dir];
                     }
                     break;
                 case 'R':
                     dir = (dir+3)%4;
                     break;
                 case 'L':
                     dir = (dir+1)%4;
                     break;
             }
             if (map[1][curr[0]][curr[1]]) {
                 for (int j = -1; j <= 1; j++) {
                     for (int k = -1; k <= 1; k++) {
                         if(isValid(curr[0]+j, curr[1]+k, n))
                             map[2][curr[0]+j][curr[1]+k] = true;
                     }
                 }
                 map[1][curr[0]][curr[1]] = false;
             }

             if(map[0][curr[0]][curr[1]] && !map[2][curr[0]][curr[1]]){
                 System.out.println("Aaaaaah!");
                 return;
             }

             // 좀비가 움직임
             for (int j = 0; j < zNum; j++) {
                 currZ = zombies.pollFirst();
                 map[0][currZ[0]][currZ[1]] = false;
                 if(!isValid(currZ[0]+dy[currZ[2]], currZ[1]+dx[currZ[2]], n)) {
                     currZ[2] = (currZ[2]+2)%4;
                 } else {
                     currZ[0] += dy[currZ[2]];
                     currZ[1] += dx[currZ[2]];
                 }
                 map[0][currZ[0]][currZ[1]] = true;
                 zombies.offerLast(currZ);
             }

             if(map[0][curr[0]][curr[1]] && !map[2][curr[0]][curr[1]]){
                 System.out.println("Aaaaaah!");
                 return;
             }
         }
        System.out.println("Phew...");
    }
}
