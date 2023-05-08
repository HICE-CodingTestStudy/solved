strings = input()
bomb = input()

stack = []
answer = ""

for string in strings:
    stack.append(string)
    if string == bomb[-1]:
        if "".join(stack[-len(bomb) :]) == bomb:
            del stack[-len(bomb) :]


answer = "".join(stack)

if len(stack) == 0:
    answer = "FRULA"

print(answer)
