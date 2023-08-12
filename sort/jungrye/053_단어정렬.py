import sys

answer = []
n = int(input())
for i in range(n):
    word = sys.stdin.readline().strip()
    if word in answer:
        continue
    answer.append(word)

answer = sorted(answer)
answer.sort(key=lambda x: len(x))
for i in answer:
    print(i, end='\n')
