# BOJ 32345 혼긱대학교
import sys

input = sys.stdin.readline

MOD = 10**9 + 7


def sol(s):
    vowels = {"a", "e", "i", "o", "u"}
    vowel_positions = []
    for i, c in enumerate(s):
        if c in vowels:
            vowel_positions.append(i)

    if not vowel_positions:
        return -1

    total = 1
    n = len(vowel_positions)

    for i in range(1, n):
        cnt = vowel_positions[i] - vowel_positions[i - 1] - 1
        total = (total * (cnt + 1)) % MOD

    return total


if __name__ == "__main__":
    t = int(input())
    for _ in range(t):
        s = input().strip()
        result = sol(s)
        print(result)
