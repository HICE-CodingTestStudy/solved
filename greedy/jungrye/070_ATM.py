n = int(input())
people = list(map(int, input().split()))
people.sort()

sum = 0
temp = 0
for i in people:
    temp += i
    sum += temp
    # print(f'sum:{sum} i:{i} temp:{temp} ')

print(sum)
