# BOJ 15483 최소 편집
import sys
input = sys.stdin.readline
    
if __name__ == "__main__":

    a = input().rstrip()
    b = input().rstrip()
    
    len_a, len_b = len(a) , len(b)
    
    # dp[i][j] : a의 i번째까지의 문자와 b의 j번째 문자열이 일치하게하는 최소 편집거리
    dp = [[0] * (len_b + 1) for _ in range(len_a + 1)]
    
    # 초기 조건 설정
    for i in range(len_a + 1):
        dp[i][0] = i
    for j in range(len_b + 1):
        dp[0][j] = j
    
    for i in range(1, len_a + 1):
        for j in range(1, len_b + 1):
            # 두 문자가 동일
            if a[i-1] == b[j-1]:
                dp[i][j] = dp[i-1][j-1]
            # 다르면 연산 적용
            else:
                dp[i][j] = min(dp[i-1][j-1] + 1,   # 교체
                               dp[i][j-1] + 1,    # 삽입
                               dp[i-1][j] + 1)    # 삭제
    
    print(dp[len_a][len_b])
    print(dp)