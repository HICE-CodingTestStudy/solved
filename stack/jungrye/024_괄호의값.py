# 풀이법 봄
import sys

brackets = sys.stdin.readline()
stack = []
answer = 0
isFirst = 1
temp = 1  # 곱셈에 사용
prev = ''

for brac in brackets:
    # 여는 괄호
    if brac == '(':
        stack.append(brac)
        temp *= 2
    elif brac == '[':
        stack.append(brac)
        temp *= 3

    # 닫는 괄호
    elif brac == ')':
        if len(stack) == 0 or stack[-1] == '[':
            answer = 0
            break
        elif prev == '(':
            answer += temp
        temp //= 2
        stack.pop()
    elif brac == ']':
        if len(stack) == 0 or stack[-1] == '(':
            answer = 0
            break
        elif prev == '[':
            answer += temp
        temp //= 3
        stack.pop()
    prev = brac  # 현재괄호를 prev에 저장


if len(stack) != 0:
    answer = 0

print(answer)
