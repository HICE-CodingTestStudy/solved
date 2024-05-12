# 0, 1, 2, ... ,    i,   i+1, ... , n-1
# |   ~ i ~    |    i   |   n-1-i  ~ |
# 1) i 보다 큰 값|       | i 보다 큰값
# 2) i 보다 작은 값|     | i 보다 큰 값
# 3) i 보다 큰 값|     | i 보다 작은 값
# 4) i 보다 작은 값|     | i 보다 작은 값   -> 불가능
# 5) 양쪽 끝 -> 항상 생존

def solution(a):
    n = len(a)
    if n == 1:
        return 1

    answer = 2

    min_left = [a[0]] + [0] * (n-1)
    min_right = [0] * (n-1) + [a[-1]]

    for i in range(1, n):
        min_left[i] = min(min_left[i - 1], a[i])
        min_right[n - 1 - i] = min(min_right[n - i], a[n - 1 - i])

    for i in range(1, n-1):
        if min_left[i - 1] > a[i] or min_right[i + 1] > a[i]:
            answer += 1

    return answer