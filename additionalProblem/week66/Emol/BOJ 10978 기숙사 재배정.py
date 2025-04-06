# BOJ 10978 기숙사 재배정
import sys

input = sys.stdin.readline


if __name__ == "__main__":
    s = [0 for _ in range(30)]
    s[2] = 1
    for i in range(3, 30):
        s[i] = (i - 1) * (s[i - 1] + s[i - 2])
    for _ in range(int(input())):
        print(s[int(input())])
