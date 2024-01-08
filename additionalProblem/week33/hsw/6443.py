# BOJ 6443
import sys
input = sys.stdin.readline

def sol(l):
    if  l == len(word):
        print("".join(ans))
        return

    for i in alphabet:
        if alphabet[i]:
            alphabet[i] -= 1
            ans.append(i)
            sol(l+1)
            alphabet[i] += 1
            ans.pop()

N = int(input())

for _ in range(N):
    alphabet = {}
    ans = []
    word = sorted(list(map(str, input().strip())))
    for letter in word:
        if letter in alphabet:
            alphabet[letter] += 1
        else:
            alphabet[letter] = 1
    sol(0)
