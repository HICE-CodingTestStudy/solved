# BOJ 1253 좋다
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    n_list = list(map(int, input().split()))
    n_list.sort()
    ans = 0

    for i in range(n):
        target = n_list[i]
        left, right = 0, n - 1

        while left < right:
            if left == i:
                left += 1
                continue
            if right == i:
                right -= 1
                continue

            current_sum = n_list[left] + n_list[right]

            if current_sum == target:
                ans += 1
                break
            elif current_sum < target:
                left += 1
            else:
                right -= 1

    print(ans)
