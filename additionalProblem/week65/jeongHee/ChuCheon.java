import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] visited;
    static class Info implements Comparable<Info>{
        int idx, up, time;
        
        public Info(int idx, int up, int time){
            this.idx = idx;
            this.up= up;
            this.time = time;
        }
        
        @Override
        public int compareTo(Info o){
            if(up!=o.up) return up-o.up;
            return time-o.time;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        visited = new int[100];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        Info[] info = new Info[100];
        Arrays.fill(info, new Info(10000,10000,10000));
        int time = 0;
        int size = 0;
        while(m-->0){
            time++;
            int a = Integer.parseInt(st.nextToken())-1;
            if(visited[a]!=0){
                visited[a]++;
                info[a].up++;
                continue;
            }
            if(size!=N){
                size++;
                visited[a]++;
                info[a] = new Info(a,1,time);
                continue;
            }
            Info[] tmp = info.clone();
            Arrays.sort(tmp);
            int idx = tmp[0].idx;
            visited[idx] = 0;
            info[idx] = new Info(10000,10000,10000);
            visited[a]++;
            info[a] = new Info(a,1,time);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<visited.length; i++){
            if(visited[i]!=0) sb.append(i+1).append(" ");
        }
        System.out.print(sb);
    }
}
