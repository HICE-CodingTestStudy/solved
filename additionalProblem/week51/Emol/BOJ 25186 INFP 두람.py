# BOJ 25186 INFP 두람
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    d_list = list(map(int, input().split()))
    num_friends = sum(d_list)
    if max(d_list) <= num_friends // 2:
        print("Happy")

    elif n == 1 and d_list[0] == 1:
        print("Happy")

    else:
        print("Unhappy")
