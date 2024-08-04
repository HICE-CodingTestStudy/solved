# BOJ 5904 Moo 게임
import sys

input = sys.stdin.readline


def moo_length(k):
    if k == 0:
        return 3
    else:
        return 2 * moo_length(k - 1) + k + 3


def find_moo(n, k):
    if k == 0:
        if n == 1:
            return "m"
        else:
            return "o"

    prev_length = moo_length(k - 1)
    middle_length = k + 3

    if n <= prev_length:
        return find_moo(n, k - 1)
    elif n <= prev_length + middle_length:
        if n == prev_length + 1:
            return "m"
        else:
            return "o"

    else:
        return find_moo(n - prev_length - middle_length, k - 1)


def solve(n):
    k = 0
    while moo_length(k) < n:
        k += 1
    return find_moo(n, k)


if __name__ == "__main__":
    n = int(input())

    # sent = [] * 1_000_000_000

    # sentence = "moo"

    # for i in range(1, 28):
    #     sentence = sentence + "m" + (i + 2) * "o" + sentence

    # print(sentence[n - 1])

    print(solve(n))
