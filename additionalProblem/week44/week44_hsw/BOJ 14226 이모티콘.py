# BOJ 14226 이모티콘
import sys
input = sys.stdin.readline
from collections import deque

# main
if __name__ == "__main__":
    
    s = int(input())
    
    # 각 노드는 (화면의 이모티콘 개수, 클립보드의 이모티콘 개수)로 구성됨
    nodes = {}
    nodes[(1, 0)] = 0
    q = deque()
    q.append((1, 0))
    
    while q:
        screen, clip = q.popleft()
        
        # 루프 종료
        if screen == s:
            print(nodes[(screen, clip)])
            break
        
        # 1. 
        if (screen, screen) not in nodes.keys():
            nodes[(screen, screen)] = nodes[(screen, clip)] + 1
            q.append((screen, screen))
        # 2.
        if (screen + clip, clip) not in nodes.keys():
            nodes[(screen+clip, clip)] = nodes[(screen, clip)] + 1
            q.append((screen+clip, clip))
        # 3.
        if (screen - 1, clip) not in nodes.keys():
            nodes[(screen-1, clip)] = nodes[(screen, clip)] + 1
            q.append((screen-1, clip))