import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Homework {
    //https://school.programmers.co.kr/learn/courses/30/lessons/176962
    //과제 진행하기
    PriorityQueue<Info> pq = new PriorityQueue<>();
    Stack<Info> left = new Stack<>();

    static class Info implements Comparable<Info> {
        String name;
        int h, m;
        int pt;

        public Info(String name, int h, int m, int pt) {
            this.name = name;
            this.h = h;
            this.m = m;
            this.pt = pt;
        }

        @Override
        public int compareTo(Info o) {
            return h * 60 + m - (o.h * 60 + o.m);
        }
    }

    public List<String> solution(String[][] plans) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            int h = Integer.parseInt(plans[i][1].split(":")[0]);
            int m = Integer.parseInt(plans[i][1].split(":")[1]);
            int pt = Integer.parseInt(plans[i][2]);
            pq.add(new Info(name, h, m, pt));
        }
        Info now = pq.poll();
        int endH = now.h + now.pt / 60;
        int endM = now.m + now.pt % 60;
        endH += endM/60;
        endM %= 60;
        while (true) {
            if (!pq.isEmpty()) {
                //끼어들 일 없을때
                if (endH * 60 + endM <= pq.peek().h * 60 + pq.peek().m) {
                    ans.add(now.name);
                    if (left.isEmpty()) now = pq.poll();
                    else if (endH * 60 + endM == pq.peek().h * 60 + pq.peek().m) {
                        now = pq.poll();
                    } else now = new Info(left.peek().name, endH, endM, left.pop().pt);
                }
                //끼어들 일 있을때
                else {
                    int time = (endH * 60 + endM) - (pq.peek().h * 60 + pq.peek().m);
                    left.push(new Info(now.name, pq.peek().h, pq.peek().m, time));
                    now = pq.poll();
                }
                endH = now.h + now.pt / 60;
                endM = now.m + now.pt % 60;
                endH += endM/60;
                endM %= 60;
            } else {
                while (true) {
                    ans.add(now.name);
                    if (left.isEmpty()) return ans;
                    now = left.pop();
                }
            }
        }
    }

    public static void main(String[] args) {
        Homework h = new Homework();
        h.solution(new String[][]{{"a", "09:00", "10"}, {"b", "09:10", "10"}, {"c", "09:15", "10"}, {"d", "09:30", "10"}, {"e", "09:35", "10"}});
    }
}
