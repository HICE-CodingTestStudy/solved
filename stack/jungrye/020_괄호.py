def solution(s):
    answer = True
    stack = []

    for i in s:
        if i == "(":
            stack.append(i)
        else:
            if len(stack) == 0:
                answer = False
                break
            else:
                stack.pop()

    if len(stack) != 0:
        answer = False

    return answer


print(solution("(())()"))  # True
print(solution(")()("))  # False
