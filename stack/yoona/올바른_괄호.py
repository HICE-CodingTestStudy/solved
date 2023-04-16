def solution(s):
    answer = True

    stack = []
    for braket in s:
        if braket == "(":
            stack.append(braket)
        elif braket == ")" and len(stack):
            stack.pop()
        else:  # 스택이 비어있는 경우
            return False

    if len(stack) > 0:
        answer = False

    return answer
