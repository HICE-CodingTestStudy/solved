# BOJ 2661 좋은수열
import sys

input = sys.stdin.readline


def is_good(seq):
    length = len(seq)

    for i in range(1, (length // 2) + 1):
        if seq[-i:] == seq[-2 * i : -i]:
            return False
    return True


def sol(seq, n):
    if len(seq) == n:
        print(seq)
        return True
    for digit in "123":
        new_seq = seq + digit
        if is_good(new_seq):
            if sol(new_seq, n):
                return True
    return False


if __name__ == "__main__":
    n = int(input().strip())
    sol("", n)
