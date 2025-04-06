# BOJ 2564 경비원
import sys


input = sys.stdin.readline


if __name__ == "__main__":
    r, c = map(int, input().split())
    n_store = int(input())

    shops = list(tuple(map(int, input().split())) for _ in range(n_store))
    d_direction, d_pos = map(int, input().split())

    total_round = 2 * (r + c)

    def dir(direction, pos):
        # 북
        if direction == 1:
            return pos

        # 남
        elif direction == 2:
            return 2 * r + c - pos

        # 서
        elif direction == 3:
            return 2 * (r + c) - pos

        # 동
        elif direction == 4:
            return pos + r

    donggun = dir(d_direction, d_pos)

    ans = 0
    for direction, pos in shops:
        shop_pos = dir(direction, pos)
        dong_store = abs(donggun - shop_pos)
        min_dis = min(dong_store, total_round - dong_store)
        ans += min_dis

    print(ans)
