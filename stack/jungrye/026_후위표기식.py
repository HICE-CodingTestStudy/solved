import sys

str = sys.stdin.readline().strip()
stack = []

for i in str:
    if i.isalpha():
        print(i, end='')
        continue
    elif i == '(':
        stack.append(i)
    elif i == '+' or i == '-':
        while True:
            if len(stack) == 0:
                break
            elif stack[-1] == '(':
                break
            print(stack.pop(), end='')
        stack.append(i)
    elif i == ')':
        while True:
            if len(stack) == 0:
                break
            elif stack[-1] == '(':
                stack.pop()
                break
            print(stack.pop(), end='')
    elif i == '*' or i == '/':
        if len(stack) != 0 and (stack[-1] == '*' or stack[-1] == '/'):
            print(stack.pop(), end='')
        stack.append(i)

while len(stack) != 0:
    print(stack.pop(), end='')
