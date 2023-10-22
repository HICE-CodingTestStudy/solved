package week19.soobin;

import java.io.*;
import java.util.*;

public class RemoveBracket {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Queue<String> formulas;

    private static void parseInput() {
        try {
            formulas = new LinkedList<>();
            formulas.add(br.readLine());
        } catch (IOException e) {}
    }

    private static void printOutput(List<String> answers) {
        try {
            for (String answer : answers) bw.write(answer + "\n");
            bw.flush();
        } catch (IOException e) {}
    }

    private static List<int[]> findEveryBracketPair(String formula) {
        Stack<Integer> stack = new Stack<>();
        List<int[]> brackets = new ArrayList<>();

        for (int i = 0; i < formula.length(); i++) {
            if (formula.charAt(i) == '(') stack.push(i);
            else if (formula.charAt(i) == ')') {
                int start = stack.pop();
                brackets.add(new int[] {start, i});
            }
        }

        return brackets;
    }

    private static String getBracketRemovedFormula(String formula, int left, int right) {
        String removedLeft = formula.substring(0, left) +
                formula.substring(left + 1);
        return removedLeft.substring(0, right - 1) +
                removedLeft.substring(right);
    }

    private static List<String> considerEveryCases() {
        Set<String> bracketRemovedFormulas = new HashSet<>();

        while (!formulas.isEmpty()) {
            String formula = formulas.poll();
            List<int[]> brackets = findEveryBracketPair(formula);

            for (int[] bracket : brackets) {
                String removedBracket = getBracketRemovedFormula(formula, bracket[0], bracket[1]);
                if (bracketRemovedFormulas.contains(removedBracket)) continue;

                bracketRemovedFormulas.add(removedBracket);
                formulas.add(removedBracket);
            }
        }

        List<String> sorted = new ArrayList<>(bracketRemovedFormulas);
        Collections.sort(sorted);
        return sorted;
    }

    public static void main(String[] args) {
        parseInput();
        List<String> answers = considerEveryCases();
        printOutput(answers);
    }
}
