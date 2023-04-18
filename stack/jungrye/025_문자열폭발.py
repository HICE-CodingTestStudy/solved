# 스택안씀, 시간초과
import sys

str = sys.stdin.readline().strip()
boom = sys.stdin.readline().strip()

while True:
    if len(str) == 0:
        print("FRULA")
        break
    if boom in str:
        str = str.replace(boom, '')
    else:
        print(str)
        break
