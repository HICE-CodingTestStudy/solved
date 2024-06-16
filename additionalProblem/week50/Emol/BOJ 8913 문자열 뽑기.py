# BOJ 8913 .문자열 뽑기
import sys

input = sys.stdin.readline


def isBlank(s):
    memo = {}

    def removeGroup(s):
        if s in memo:
            return memo[s]

        n = len(s)
        if n < 2:
            memo[s] = False
            return False

        # 그룹찾기
        i = 0
        while i < n:
            j = i
            # 같은 문자 연속부분 찾기
            while j < n and s[i] == s[j]:
                j += 1
            # 해당 부분의 길이 2 이상
            if j - i > 1:
                # 제거한 나머지를 new_s에 저장
                new_s = s[:i] + s[j:]
                if new_s == "" or removeGroup(new_s):
                    memo[s] = True
                    return True
            i = j

        # 제거 안됨
        memo[s] = False
        return False

    return removeGroup(s)


if __name__ == "__main__":

    T = int(input())
    results = []
    for _ in range(T):
        s = input().strip()
        if isBlank(s):
            results.append(1)
        else:
            results.append(0)
    for result in results:
        print(result)
