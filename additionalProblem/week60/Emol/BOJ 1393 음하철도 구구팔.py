# BOJ 1393 음하철도 구구팔
import sys, math

input = sys.stdin.readline


def sol(x, y):
    return ((x - xs) ** 2 + (y - ys) ** 2) ** 0.5


if __name__ == "__main__":
    xs, ys = map(int, input().split())
    xe, ye, dx, dy = map(int, input().split())

    d_gcd = math.gcd(dx, dy)
    dx, dy = dx // d_gcd, dy // d_gcd

    while sol(xe, ye) > sol(xe + dx, ye + dy):
        xe += dx
        ye += dy

    print(xe, ye)
