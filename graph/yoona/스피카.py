from collections import defaultdict

dic = defaultdict(list)
arr = []
for _ in range(12):
    x, y = map(int, input().split())

    dic[x].append(y)
    dic[y].append(x)

for i in dic:
    if len(dic[i]) != 3:
        continue
    sum = 0
    for j in dic[i]:
        sum += len(dic[j])

    if sum == 6:
        print(i)
        break
