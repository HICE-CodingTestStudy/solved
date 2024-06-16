# BOJ 2143 두 배열의 합
import sys, bisect as bi

# def binarySearch(target, data):
#     r, l = 0, len(data) - 1
#     while r <= l:
#         m = r + l
#         if data[m] == target:
#             return m
#         elif data[m] > target:
#             e = m - 1
#         else:
#             s = m + 1
#     return

input = sys.stdin.readline


# 누적합 생성 알고리즘
def getSubarraySums(arr):
    subarraySums = []
    n = len(arr)
    for i in range(n):
        temp = 0
        for j in range(i, n):
            temp += arr[j]
            subarraySums.append(temp)
    return subarraySums


if __name__ == "__main__":
    t = int(input())
    n = int(input())
    a = list(map(int, input().split()))
    m = int(input())
    b = list(map(int, input().split()))

    # a, b배열의 누적합 배열 생성
    AS = getSubarraySums(a)
    BS = getSubarraySums(b)

    # T = AS[i] + BS[j] --> BS[j] == T - AS[i]
    BS.sort()
    ans = 0
    # 정렬된 BS 기준으로 해당 값이 리스트에서 등장하는 횟수 카운트
    for i in range(len(AS)):
        l = bi.bisect_left(BS, t - AS[i])
        r = bi.bisect_right(BS, t - AS[i])
        ans += r - l
    print(ans)
