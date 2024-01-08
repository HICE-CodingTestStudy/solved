import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Diet {
    private static class Ingredient {
        int protein, fat, carbohydrate, vitamin, price;

        public Ingredient() {
            this.protein = 0;
            this.fat = 0;
            this.carbohydrate = 0;
            this.vitamin = 0;
            this.price = 0;
        }

        public Ingredient(String[] input) {
            this.protein = Integer.parseInt(input[0]);
            this.fat = Integer.parseInt(input[1]);
            this.carbohydrate = Integer.parseInt(input[2]);
            this.vitamin = Integer.parseInt(input[3]);
            this.price = Integer.parseInt(input[4]);
        }

        public void sum(Ingredient target) {
            this.protein += target.protein;
            this.fat += target.fat;
            this.carbohydrate += target.carbohydrate;
            this.vitamin += target.vitamin;
            this.price += target.price;
        }

        public boolean isValid() {
            return this.protein >= mp
                    && this.fat >= mf
                    && this.carbohydrate >= ms
                    && this.vitamin >= mv;
        }
    }

    private static class Answer {
        Queue<String> indices;
        int price;

        public Answer() {
            this.indices = new PriorityQueue<>();
            this.price = Integer.MAX_VALUE;
        }

        private String parseIndices(boolean[] visited) {
            String indices = "";
            for (int i = 1; i <= N; i++)
                if (visited[i]) indices += i + " ";
            return indices;
        }

        public void updateAnswer(int price, boolean[] visited) {
            if (price < this.price) indices.clear();
            this.price = Math.min(this.price, price);
            this.indices.add(parseIndices(visited));
        }

        public void print() {
            try {
                price = price == Integer.MAX_VALUE ? -1 : price;
                bw.write(price + "\n");
                if (price != -1) bw.write(indices.poll());
                bw.flush();
            } catch (IOException e) {}
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Ingredient[] ingredients;
    private static Ingredient temp;
    private static Answer answer = new Answer();
    private static int N, mp, mf, ms, mv;

    private static void initMinIngredients(String[] input) {
        mp = Integer.parseInt(input[0]);
        mf = Integer.parseInt(input[1]);
        ms = Integer.parseInt(input[2]);
        mv = Integer.parseInt(input[3]);
    }

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            ingredients = new Ingredient[N + 1];
            String[] input = br.readLine().split(" ");
            initMinIngredients(input);

            for (int i = 1; i <= N; i++) {
                input = br.readLine().split(" ");
                ingredients[i] = new Ingredient(input);
            }
        } catch (IOException e) {}
    }

    private static boolean isValid(boolean[] visited) {
        temp = new Ingredient();

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) continue;
            temp.sum(ingredients[i]);
        }

        return temp.isValid() && temp.price <= answer.price;
    }

    private static void findCombination(boolean[] visited, int start, int r) {
        if (r == 0 && isValid(visited)) {
            answer.updateAnswer(temp.price, visited);
            return;
        }

        for (int i = start; i <= N; i++) {
            visited[i] = true;
            findCombination(visited, i + 1, r - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        parseInput();
        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            findCombination(visited, 1, i);
        }
        answer.print();
    }
}
