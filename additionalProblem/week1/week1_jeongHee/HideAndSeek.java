import java.util.*;

public class Main {
    //https://www.acmicpc.net/problem/12851
    //숨바꼭질 2
    static int time = 0;
    static int count = 0;
    public static void bfs(int s, int b){
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer,Integer> visited = new HashMap<>();
        queue.add(s);
        if(s==b) {
            count++;
            return;
        }
        visited.put(s,time);
        while (!queue.isEmpty()){
            time++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int next = queue.poll();
                if(visited.get(next+1)==null||(visited.get(next+1)==time||next+1==b)) {
                    if(next+1==b) count++;
                    else if(next+1>=0 && next +1<=100000){
                        queue.add(next + 1);
                        visited.put(next + 1, time);
                    }
                }
                if(visited.get(next-1)==null||(visited.get(next-1)==time||next-1==b)) {
                    if(next-1==b) count++;
                    else if(next-1>=0 && next -1<=100000){
                        queue.add(next - 1);
                        visited.put(next - 1, time);
                    }
                }
                if(visited.get(next*2)==null||(visited.get(next*2)==time||next*2==b)) {
                    if(next*2 ==b) count++;
                    else if(next*2>=0 && next *2<=100000){
                        queue.add(next * 2);
                        visited.put(next * 2, time);
                    }

                }
            }
            if(count!=0) return;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int b = sc.nextInt();
        bfs(s,b);
        System.out.println(time);
        System.out.println(count);
    }
}