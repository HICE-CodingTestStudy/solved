package soobin;

import java.util.Stack;

class StockInfo {
    private int time;
    private int price;

    public StockInfo(int time, int price) { this.time = time; this.price = price; }
    public int getPrice() { return price; }
    public int getTime() { return time; }
}

public class Stock {
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<StockInfo> stack = new Stack<>();

        stack.push(new StockInfo(0, prices[0]));
        for(int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            answer[i] = 0;

            if(!stack.empty() && stack.peek().getPrice() > currentPrice) {
                while(!stack.empty() && stack.peek().getPrice() > currentPrice) {
                    StockInfo down = stack.pop();
                    answer[down.getTime()] = i - down.getTime();
                }
            }

            stack.push(new StockInfo(i, currentPrice));
        }

        for(StockInfo stock : stack) answer[stock.getTime()] = (prices.length - 1) - stock.getTime();

        return answer;
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,3,3};
        int[] answer = solution(prices);
        for(int n : answer) System.out.print(String.format("%d ", n));
    }
}
