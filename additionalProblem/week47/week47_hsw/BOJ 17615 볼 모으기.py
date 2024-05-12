# BOJ 17615 볼 모으기
import sys
input = sys.stdin.readline


if __name__ == "__main__":
    n = int(input())
    
    balls = input().strip()
    
    cnt_list = []
    # 어차피 한 곳으로 몰아넣는거면, 갯수만 세도 됨
    cnt_list.append(balls.rstrip('R').count('R'))
    cnt_list.append(balls.rstrip('B').count('B'))
    cnt_list.append(balls.lstrip('R').count('R'))
    cnt_list.append(balls.lstrip('B').count('B'))

    print(min(cnt_list))
    