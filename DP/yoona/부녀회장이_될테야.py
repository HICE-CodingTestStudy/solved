T = int(input())

for i in range(T):
    k = int(input())
    n = int(input())

    arr = [[i + 1 for i in range(n)]]

    for floor in range(1, k + 1):
        li = []
        for room in range(1, n + 1):
            li.append(sum(arr[floor - 1][:room]))
        arr.append(li)
    print(arr[k][n - 1])
