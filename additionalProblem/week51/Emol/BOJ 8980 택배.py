# BOJ 8980 택배.py
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    n, c = map(int, input().split())
    m = int(input())
    info = []
    for _ in range(m):
        depart, dest, boxNum = map(int, input().split())
        info.append((depart, dest, boxNum))
    info.sort(key=lambda x: (x[1], -x[0]))
    capa = [c] * (n)
    ans = 0
    # print(f"info:{info}")

    for i in range(m):
        flag = False
        dep, des, boxN = info[i][0], info[i][1], info[i][2]
        dep -= 1
        des -= 1
        temp = boxN

        for j in range(dep, des):
            if capa[j] <= 0:
                flag = True
                break
            temp = min(temp, capa[j])

        if flag:
            continue

        # print(f"temp:{temp}")
        if temp != 0:

            if temp > boxN:
                for j in range(dep, des):
                    capa[j] -= boxN
                ans += boxN
                # print(f"if temp >= boxN : ans = {ans}")
                # print(f"capa:{capa}")

            elif temp <= boxN:
                for j in range(dep, des):
                    capa[j] -= temp
                ans += temp
                # print(f"if temp < boxN : ans = {ans}")
                # print(f"capa:{capa}")
    print(ans)
