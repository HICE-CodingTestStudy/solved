# BOJ 1135 뉴스 전하기
import sys
input = sys.stdin.readline

def dfs(node):
    if not pred_list[node]:
        return 0
    ans = list()
    for hubae in pred_list[node]:
        ans.append(dfs(hubae))
    ans.sort(reverse=True)
    ans = [ans[i] + i + 1 for i in range(len(pred_list[node]))]
    return max(ans)

if __name__ == "__main__":
    n = int(input())
    order = list(map(int, input().split()))
    pred_list =  [[] for _ in range(n)]
    for i in range(1, n):
        sunbae = order[i]
        pred_list[sunbae].append(i)
 
    print(dfs(0))
        