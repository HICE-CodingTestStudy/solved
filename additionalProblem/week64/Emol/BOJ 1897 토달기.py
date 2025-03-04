# BOJ 1897 토달기
import sys
from collections import deque, defaultdict

input = sys.stdin.readline


if __name__ == "__main__":
    d, s = map(str, input().split())
    d = int(d)
    words = defaultdict(list)
    for _ in range(d):
        word = input().strip()
        if len(word) <= 3:
            continue
        words[len(word)].append(word)

    q = deque([s])
    ans = s
    visited = defaultdict(set)

    while q:
        cur = q.popleft()
        ans = cur
        next_len = len(cur) + 1
        if next_len not in words:
            continue
        for nxt in words[next_len]:
            if nxt in visited[next_len]:
                continue
            i, j, diff = 0, 0, 0
            while i < len(cur) and j < len(nxt) and diff <= 1:
                if cur[i] != nxt[j]:
                    diff += 1
                    j += 1
                else:
                    i += 1
                    j += 1

            if diff <= 1 and i == len(cur):
                q.append(nxt)
                visited[next_len].add(nxt)

    print(ans)
