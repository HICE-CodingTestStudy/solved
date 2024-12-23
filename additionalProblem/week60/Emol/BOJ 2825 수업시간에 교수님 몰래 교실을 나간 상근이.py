# BOJ 2825 수업시간에 교수님 몰래 교실을 나간 상근이
import sys

input = sys.stdin.readline


def sol(numbers):
    bit_list = [0] * 1024
    for num in numbers:
        bitmask = 0
        for digit in num.strip():
            bitmask |= 1 << int(digit)
        bit_list[bitmask] += 1

    result = 0
    for i in range(1024):
        if bit_list[i] >= 2:
            result += (bit_list[i] * (bit_list[i] - 1)) // 2
        for j in range(i + 1, 1024):
            if i & j:
                result += bit_list[i] * bit_list[j]
    return result


if __name__ == "__main__":
    n = int(input())
    numbers = [input() for _ in range(n)]

    print(sol(numbers))
