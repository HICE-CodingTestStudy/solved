import sys

pipes = sys.stdin.readline()
stack = []

sum = 0
check = 0  # 바로앞이 여는괄호면 0

for pipe in pipes:
    if pipe == '(':
        stack.append(pipe)
        check = 0
    elif pipe == ')':
        if check == 0:
            stack.pop()
            sum += len(stack)
        else:
            stack.pop()
            sum += 1
        check = 1

print(sum)
