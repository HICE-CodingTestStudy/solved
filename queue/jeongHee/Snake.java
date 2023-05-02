package queue;

import java.util.*;

public class Snake {
    //https://www.acmicpc.net/problem/3190
    //뱀

    public static void main(String[] args) {
        Deque<int[]> snake = new ArrayDeque<>();
        Map<String,Integer> snakeMap = new HashMap<>();
        Map<String,Integer> appleMap = new HashMap<>();
        Map<Integer,Character> turn = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        for (int i = 0; i < K; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(sc.nextInt()).append(" ").append(sc.nextInt());
            appleMap.put(sb.toString(),1);
        }
        int ans = 1;
        int[] start = {1,1};
        int L = sc.nextInt();
        for (int i = 0; i < L; i++) {
            turn.put(sc.nextInt(),sc.next().charAt(0));
        }
        char head = 'R';
        snake.addLast(start);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= N+1; i++) {
            for (int j = 0; j <= N+1; j++) {
                if(i==0||j==0||i==N+1||j==N+1){
                    sb.append(i).append(" ").append(j);
                    snakeMap.put(sb.toString(),1);
                    sb = new StringBuilder();
                }
            }
        }
        snakeMap.put("1 1",1);
        while (true){

            switch (head){
                case 'R':
                    snake.addFirst(new int[]{snake.peekFirst()[0] , snake.peekFirst()[1]+1});
                    break;
                case 'L':
                    snake.addFirst(new int[]{snake.peekFirst()[0], snake.peekFirst()[1] -1});
                    break;
                case 'U':
                    snake.addFirst(new int[]{snake.peekFirst()[0] -1 , snake.peekFirst()[1]});
                    break;
                case 'D':
                    snake.addFirst(new int[]{snake.peekFirst()[0] +1, snake.peekFirst()[1]});
                    break;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(snake.peekFirst()[0]).append(" ").append(snake.peekFirst()[1]);
            if(snakeMap.containsKey(sb2.toString())&&snakeMap.get(sb2.toString()).equals(1)){
                System.out.println(ans);
                return;
            } else {
                snakeMap.put(sb2.toString(), 1);
            }
            if(appleMap.containsKey(sb2.toString())&&appleMap.get(sb2.toString()).equals(1)){
                appleMap.put(sb2.toString(),-1);
            }else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(snake.peekLast()[0]).append(" ").append(snake.peekLast()[1]);
                snakeMap.put(sb3.toString(),-1);
                snake.pollLast();
            }

            if(turn.containsKey(ans)){
                char now = turn.get(ans);
                switch (head){
                    case 'R':
                        if(now=='L')
                            head = 'U';
                        else head ='D';
                        break;
                    case 'L':
                        if(now=='L')
                            head = 'D';
                        else head = 'U';
                        break;
                    case 'U':
                        if(now=='L')
                            head = 'L';
                        else head = 'R';
                        break;
                    case 'D':
                        if(now=='L')
                            head = 'R';
                        else head = 'L';
                        break;
                }

            }
            ans++;
        }
    }
}
