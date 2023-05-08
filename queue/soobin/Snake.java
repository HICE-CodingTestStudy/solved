package soobin;

import java.io.*;
import java.util.*;

enum Type { SPACE, SNAKE, APPLE }

enum Direction {
    RIGHT(0),
    DOWN(1),
    LEFT(2),
    UP(3);

    private final int direction;

    Direction(int direction) {
        this.direction = direction;
    }
    static int adjust(int n) {
        if(n == 4) return 0;
        if(n == -1) return 3;
        return n;
    }
    public int getDirection() { return direction; }
    public static Direction valueOf(int n) {
        final int adjusted = adjust(n);
        return Arrays.stream(values())
                .filter(direction -> direction.direction == adjusted)
                .findAny()
                .orElse(null);
    }
}

class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
}

class Turn {
    private int time;
    private boolean direction;

    public Turn(int time, char dirChar) {
        this.time = time;
        this.direction = dirChar == 'D';
    }
    public int getTime() { return time; }
    public boolean getDirection() { return direction; }
}

public class Snake {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int currentX = 0;
    private static int currentY = 0;
    private static Direction currentDirection = Direction.RIGHT;

    private static void move() {
        switch (currentDirection) {
            case RIGHT: currentY++; break;
            case DOWN: currentX++; break;
            case LEFT: currentY--; break;
            case UP: currentX--; break;
        }
    }

    private static int play(Type[][] board, Queue<Turn> turns, int N) {
        Deque<Coordinate> snake = new LinkedList<>();
        snake.addFirst(new Coordinate(0, 0));
        int answer = 0;

        while(true) {
            answer++;
            // 머리를 다음 칸으로 이동
            move();
            // 게임 종료 조건
            if(currentX >= N || currentX < 0 || currentY >= N || currentY < 0 || board[currentX][currentY] == Type.SNAKE) break;
            snake.addFirst(new Coordinate(currentX, currentY));

            // 꼬리 이동
            if(board[currentX][currentY] != Type.APPLE) {
                Coordinate tail = snake.pollLast();
                board[tail.getX()][tail.getY()] = Type.SPACE;
            }
            board[currentX][currentY] = Type.SNAKE;

            // 방향 전환 확인
            if(!turns.isEmpty() && answer == turns.peek().getTime()) {
                Turn turn = turns.poll();
                currentDirection = turn.getDirection()
                        ? Direction.valueOf(currentDirection.getDirection() + 1)
                        : Direction.valueOf(currentDirection.getDirection() - 1);
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Type[][] board = new Type[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                board[i][j] = Type.SPACE;

        // 게임판에 사과 올리기
        int apples = Integer.parseInt(br.readLine());
        for(int i = 0; i < apples; i++) {
            Scanner scanner = new Scanner(br.readLine());
            board[scanner.nextInt() - 1][scanner.nextInt() - 1] = Type.APPLE;
        }

        // 방향 전환 저장
        int turnCount = Integer.parseInt(br.readLine());
        Queue<Turn> turns = new LinkedList<>();
        for(int i = 0; i < turnCount; i++) {
            Scanner scanner = new Scanner(br.readLine());
            turns.add(new Turn(scanner.nextInt(), scanner.next().charAt(0)));
        }

        bw.write(String.valueOf(play(board, turns, N)));
        bw.flush();
    }
}
