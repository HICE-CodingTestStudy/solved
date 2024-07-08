# BOJ 28018 시간이 겹칠까
import sys

input = sys.stdin.readline
MAX_TIME = 1_000_000

if __name__ == "__main__":
    n = int(input())
    diff = [0] * (MAX_TIME + 2)

    for _ in range(n):
        s, e = map(int, input().split())
        diff[s] += 1
        if e + 1 <= MAX_TIME:
            diff[e + 1] -= 1

    accu = [0] * (MAX_TIME + 1)
    accu[0] = diff[0]
    for i in range(1, MAX_TIME + 1):
        accu[i] = accu[i - 1] + diff[i]

    q = int(input())
    q_list = list(map(int, input().split()))
    for query in q_list:
        print(accu[query])
