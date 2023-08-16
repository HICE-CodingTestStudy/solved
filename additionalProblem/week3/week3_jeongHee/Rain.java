package queue;

import java.util.*;

public class Rain {
    //https://www.acmicpc.net/problem/14719
    //빗물
    static class Coordinate{
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int W = sc.nextInt();
        int[] wall = new int[W];
        for (int i = 0; i < W; i++) {
            wall[i]=sc.nextInt();
        }

//        int rain = 0;
//        Stack<Coordinate> stack = new Stack<>();
//        stack.add(new Coordinate(0,wall[0]));
//        for (int i = 1; i < W; i++) {
//            if (!stack.isEmpty() && stack.peek().y>=wall[i]){
//               stack.add(new Coordinate(i,wall[i]));
//               continue;
//            }
//            Stack<Coordinate> changedWall = new Stack<>();
//            changedWall.add(new Coordinate(i, wall[i]));
//            Coordinate c = null;
//            if(stack.size()==1){
//                stack.add(new Coordinate(stack.pop().x,wall[i]));
//                stack.add(new Coordinate(i,wall[i]));
//                continue;
//            }
//            while (stack.size()>=2 && stack.peek().y < wall[i]){
//                c = stack.pop();
//                changedWall.add(new Coordinate(c.x, wall[i]));
//            }
//
//            if(c==null) continue;
//            int minWall = Math.min(stack.peek().y,wall[i]);
//            rain += Math.abs(minWall-c.y) * (Math.abs(i-stack.peek().x)-1);
//            while (changedWall.size()!=1){
//                stack.add(new Coordinate(changedWall.pop().x,minWall));
//            }
//            stack.add(changedWall.pop());
//        }
//        System.out.println(rain);


//        System.out.println(rain);
//        int max = wall[0];
//        int upCount = 0;
//        int downCount = 0;
//        int state = 0;
//        ArrayList<Integer> piece = new ArrayList<>();
//        for (int i = 1; i < W; i++) {
//            if(i==1){
//                if(wall[i]>wall[i-1]){
//                    max = wall[i];
//                    state =1;
//                    upCount ++;
//                }
//                else if (wall[i]<wall[i-1]){
//                    state = -1;
//                    downCount++;
//                }
//            }
//            if(wall[i]<wall[i-1]){
//
//            }
//        }

        //int[][] wallSeg = new int[500][500];
        ArrayList<Deque<Integer>> wallSeg = new ArrayList<>();
        for (int i = 0; i < H+1; i++) {
            wallSeg.add(new ArrayDeque<>());
        }
        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j <= wall[i]; j++) {
                wallSeg.get(j).add(i);
            }
        }
        int rain = 0;
        for (int i = 1; i < H+1; i++) {
            Deque<Integer> deque = wallSeg.get(i);
            if(deque.size()<2) continue;
            int start = deque.peekFirst();
            int end = deque.peekLast();
            rain += end-start+1-deque.size();
        }
        System.out.println(rain);
    }
}
