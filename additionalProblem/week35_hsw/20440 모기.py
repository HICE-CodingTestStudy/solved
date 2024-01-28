import sys
input = sys.stdin.readline

n = int(input())
mos = {}
for _ in range(n):
    Te, Tx = map(int, input().split())
    mos[Te] = mos.get(Te, 0) + 1
    mos[Tx] = mos.get(Tx, 0) - 1

mostime = sorted(mos.keys())
maxmos, summos, flag = -1, 0, False
maxmostime = [None, None]

for time in mostime:
    summos += mos[time]
    if summos > maxmos:
        maxmos = summos
        maxmostime[0] = time
        flag = True

    elif summos < maxmos and flag:
        maxmostime[1] = time
        flag = False

print(maxmos)
print(maxmostime[0], maxmostime[1])
