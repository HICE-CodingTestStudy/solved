# BOJ 16457 단풍잎 이야기
import sys
from itertools import combinations as comb

input = sys.stdin.readline

if __name__ == "__main__":
    n, m, k = map(int, input().split())
    quests = []

    for _ in range(m):
        quest = set(map(int, input().split()))
        quests.append(quest)

    skills = set(range(1, 2 * n + 1))

    max_count = 0

    for case in comb(skills, n):
        case_set = set(case)
        count = 0

        for quest in quests:
            if quest <= case_set:
                count += 1

        max_count = max(max_count, count)

    print(max_count)
