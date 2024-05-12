import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShoppingMall {
    //https://www.acmicpc.net/problem/17612
    //쇼핑몰
    static int N, K;
    static Queue<CustomerInfo> waiting = new ArrayDeque<>();
    static PriorityQueue<CashInfo> pay, done;

    static class CustomerInfo implements Comparable<CustomerInfo> {
        int id;
        int w;

        public CustomerInfo(int id, int w) {
            this.id = id;
            this.w = w;
        }

        @Override
        public int compareTo(CustomerInfo o) {
            if (w != o.w) {
                return w - o.w;
            }
            return o.id - id;
        }

    }

    static class CashInfo {
        int cashIndex;
        int finishTime;
        int customerIndex;

        public CashInfo(int cashIndex, int finishTime, int customerIndex) {
            this.cashIndex = cashIndex;
            this.finishTime = finishTime;
            this.customerIndex = customerIndex;
        }

    }

    static Comparator<CashInfo> forPay = new Comparator<CashInfo>() {
        @Override
        public int compare(CashInfo o1, CashInfo o2) {
            if (o1.finishTime != o2.finishTime)
                return o1.finishTime - o2.finishTime;
            return o1.cashIndex - o2.cashIndex;
        }
    };

    static Comparator<CashInfo> forDone = new Comparator<CashInfo>() {
        @Override
        public int compare(CashInfo o1, CashInfo o2) {
            if (o1.finishTime != o2.finishTime)
                return o1.finishTime - o2.finishTime;
            return o2.cashIndex - o1.cashIndex;
        }
    };

    static long solution() {
        long ans = 0;
        for (int i = 0; i < K; i++) {
            pay.add(new CashInfo(i, 0, -1));
        }
        while (!waiting.isEmpty()) {
            CashInfo cashier = pay.poll();
            CustomerInfo customer = waiting.poll();
            pay.add(new CashInfo(cashier.cashIndex, cashier.finishTime + customer.w, customer.id));
            if (cashier.customerIndex == -1) continue;
            done.add(cashier);
        }
        while (!pay.isEmpty()) {
            CashInfo c = pay.poll();
            if (c.customerIndex != -1)
                done.add(c);
        }
        int count = 1;
        while (!done.isEmpty()) {
            ans += ((long) done.poll().customerIndex) * (long) count;
            count++;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        pay = new PriorityQueue<>(forPay);
        done = new PriorityQueue<>(forDone);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            waiting.add(new CustomerInfo(a, b));
        }
        System.out.println(solution());
    }
}
