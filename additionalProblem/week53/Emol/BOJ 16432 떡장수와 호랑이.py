# BOJ 16432 떡장수와 호랑이
import sys

sys.setrecursionlimit(10**4)
input = sys.stdin.readline


def dfs(day, tiger, last_ddeok):
    if day == n:
        return tiger.strip().split()

    for ddeok in ddeoks[day]:
        ddeok_num = int(ddeok)
        if day == 0 or (ddeok_num != last_ddeok and not visited[day][ddeok_num]):
            visited[day][ddeok_num] = True
            result = dfs(day + 1, tiger + ddeok + " ", ddeok_num)
            if result:
                return result
            visited[day][ddeok_num] = False
    return None


if __name__ == "__main__":
    n = int(input())

    ddeoks = []

    for _ in range(n):
        temp = list(input().split())
        m, ddeok = temp[0], temp[1:]
        ddeoks.append(ddeok)

    visited = [[False] * 10 for _ in range(n)]

    result = dfs(0, "", -1)

    print("\n".join(result) if result else "-1")
