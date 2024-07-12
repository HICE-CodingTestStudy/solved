# BOJ 21921 블로그
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    n, x = map(int, input().split())
    visitors = list(map(int, input().split()))

    current_sum = sum(visitors[:x])
    max_sum = current_sum
    count = 1

    for i in range(x, n):
        current_sum += visitors[i] - visitors[i - x]
        if current_sum > max_sum:
            max_sum = current_sum
            count = 1
        elif current_sum == max_sum:
            count += 1

    if max_sum == 0:
        print("SAD")
    else:
        print(max_sum)
        print(count)
