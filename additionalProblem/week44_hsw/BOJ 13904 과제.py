# BOJ 13904 과제
import sys
input = sys.stdin.readline

# main
if __name__ == "__main__":
    # 입력 처리
    n = int(input())
    
    works = []
    for _ in range(n):
        d, w = map(int, input().split())
        works.append((d, w))
        
    works.sort(key = lambda x: x[1], reverse = True)
    
    ans = 0
    solved = [False] * 1001
    
    for d, w in works:
        today = d
        # 할 과제 날짜 탐색
        while today > 0 and solved[today]:
            today -= 1
            
        # 가능한 날짜가 없다면 패스
        if today == 0:
            continue
        else: # 가능한 날짜가 있다면 solved배열 갱신 후 점수 합산
            solved[today] = True
            ans += w
            
    print(ans)
