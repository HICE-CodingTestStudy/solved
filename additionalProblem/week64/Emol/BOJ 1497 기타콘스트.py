# BOJ 1497 기타콘스트
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    n, m = map(int, input().split())
    guitars = []
    for _ in range(n):
        name, playable = map(str, input().split())
        mask = 0
        for i, letter in enumerate(playable):
            if letter == "Y":
                mask |= 1 << i
        guitars.append(mask)

    ssapsong = 0
    ssapguitar = 1e9

    for case in range(1, 1 << n):
        unimask = 0
        count_guitar = 0
        for j in range(n):
            if case & (1 << j):
                unimask |= guitars[j]
                count_guitar += 1

        song = bin(unimask).count("1")

        if song > ssapsong:
            ssapsong = song
            ssapguitar = count_guitar

        elif song == ssapsong:
            ssapguitar = min(ssapguitar, count_guitar)

    print(-1 if ssapsong == 0 else ssapguitar)
