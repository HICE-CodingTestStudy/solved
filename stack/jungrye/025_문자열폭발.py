import sys

str = sys.stdin.readline().strip()
boom = sys.stdin.readline().strip()
reversedBoom = "".join(reversed(boom))
stack = []

for s in str:
    if s == boom[-1]:  # 폭발 문자열의 마지막 문자와 같은지 검사
        stack.append(s)
        isBoom = ''.join(stack[-1:-len(boom)-1:-1])
        if isBoom == reversedBoom:
            for i in range(len(boom)):
                stack.pop()
    else:
        stack.append(s)

if len(stack) == 0:
    print("FRULA")
else:
    print(''.join(stack))
