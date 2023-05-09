package soobin;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Element implements Comparable<Element> {
    private int id;
    private int value;

    public Element(int id, int value) { this.id = id; this.value = value; }
    public int getId() { return id; }
    public int getValue() { return value; }

    @Override
    public int compareTo(Element o) {
        return this.value > o.getValue() ? -1 : 1;
    }
}

public class Treasure {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static void parseStringToIntList(String str, List<Integer> list) {
        Scanner scanner = new Scanner(str);
        for(int i = 0; scanner.hasNext(); i ++) list.add(scanner.nextInt());
    }

    private static void parseStringToElementList(String str, List<Element> list) {
        Scanner scanner = new Scanner(str);
        for(int i = 0; scanner.hasNext(); i++) list.add(new Element(i, scanner.nextInt()));
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Integer> A = new ArrayList<>();
        List<Element> B = new ArrayList<>();
        parseStringToIntList(br.readLine(), A);
        parseStringToElementList(br.readLine(), B);

        Collections.sort(A);
        Collections.sort(B);
        int[] answerArr = new int[N];
        int i = 0;
        for ( Element element : B ) answerArr[element.getId()] = A.get(i++) * element.getValue();

        int answer = 0;
        for ( int n : answerArr ) answer += n;
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
