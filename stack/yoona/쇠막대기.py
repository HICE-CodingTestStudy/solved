import sys

strings = sys.stdin.readline().strip()  # readline()은 개행문자 포함 -> strip()으로 공백 제거
stack = []
answer = 0
for index, string in enumerate(strings):
    if string == "(":
        stack.append(string)
        continue

    # 레이저
    if strings[index - 1] == "(":
        stack.pop()
        answer += len(stack)

    else:
        stack.pop()
        answer += 1

print(answer)
