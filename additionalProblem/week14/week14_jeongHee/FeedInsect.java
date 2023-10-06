package com.example.demo.heap;

import java.text.DecimalFormat;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FeedInsect {
    //https://www.acmicpc.net/problem/14488
    //준오는 급식충이야!!
    static int N;
    static double T;

    static class Student implements Comparable<Student> {
        double location;
        double v;

        @Override
        public int compareTo(Student o) {
            if (this.v - o.v > 0)
                return 1;
            else if (this.v == o.v)
                return 0;
            else return -1;
        }

        public Student(double location, double v) {
            this.location = location;
            this.v = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        T = sc.nextDouble();
        PriorityQueue<Student> pq = new PriorityQueue<>();
        double[] location = new double[N];
        double[] v = new double[N];
        double start = 0, end = 1000000000;

        //부동소수점 오차 처리
        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i = 0; i < N; i++) {
            location[i] = sc.nextDouble();
        }
        for (int i = 0; i < N; i++) {
            v[i] = sc.nextDouble();
        }
        for (int i = 0; i < N; i++) {
            pq.add(new Student(location[i], v[i]));
        }
        while (!pq.isEmpty()) {
            Student student = pq.poll();
            double nowStart = Double.parseDouble(df.format(student.location - student.v * T));
            double nowEnd = Double.parseDouble(df.format(student.location + student.v * T));
            if (nowStart > start) start = nowStart;
            if (nowEnd < end) end = nowEnd;
            if (end < start) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }
}
