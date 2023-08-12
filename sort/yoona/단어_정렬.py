import sys

input = sys.stdin.readline

N = int(input())
words = []

for _ in range(N):
    words.append(input().strip())

words = sorted(set(words), key=lambda x: (len(x), x))
for word in words:
    print(word)
