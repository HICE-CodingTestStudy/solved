strings = input()

stack = [strings[0]]
index = 1

while stack:
    if strings[index] in ['(', '[']:
        stack.append(strings[index])
    
    elif strings[index] == ')':
        if stack[-1] == '(':
            stack.pop()
            stack.append(2)
            