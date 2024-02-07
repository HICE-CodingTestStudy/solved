#BOJ 2263 트리의 순
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 5)
n = int(input())

inorder = list(map(int, input().split()))
postorder = list(map(int, input().split()))

tree = [0] * (n+1)
for i in range(n):
    tree[inorder[i]] = i

# 분할정복
def preorder(instart, inend, poststart, postend):
    if (instart > inend) or (poststart > postend):
        return
    
    # postorder의 마지막은 트리의 루트
    root = postorder[postend]
    
    # inorder 기준으로 갈라지는 트리
    leftnode = tree[root] - instart
    rightnode = inend - tree[root]


    print(root, end = " ")
    #분할정복으로 트리 추척
    #왼쪽
    preorder(instart, instart+leftnode-1, poststart, poststart+leftnode-1)
    #오른쪽
    preorder(inend-rightnode+1, inend, postend-rightnode, postend-1)

preorder(0, n-1, 0, n-1)

