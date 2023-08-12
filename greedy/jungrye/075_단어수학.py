N = int(input())
words = []
dict = {'0': '0'}

for i in range(N):
    words.append(input())
words.sort(key=len, reverse=True)
maxLength = len(words[0])

# print(maxLength)
# print(words)

num = 9
for s in range(len(words)):
    if len(words[s]) < maxLength:
        words[s] = '00'+words[s]

for i in range(maxLength):
    for j in range(len(words)):
        if words[j][i] not in dict.keys():
            dict[words[j][i]] = str(num)
            num -= 1

listst = []
for s in words:
    newS = []
    for i in s:
        newS.append(dict[i])
    listst.append(int(''.join(newS)))
print(listst)
print(sum(listst))


# N개의 단어
# 각 단어는 알파벳 대문자
# 알파벳 대문자를 0-9의 숫자중하나로 바꿔서 N개의 수를 합하는 문제
# 같알 -> 같숫, 겹치면안됨
# 최대로만들기
