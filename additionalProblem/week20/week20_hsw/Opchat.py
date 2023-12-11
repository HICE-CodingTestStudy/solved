def solution(record):
dic = {}
# UID - nickname 형태의 dic 완성
for line in record:
    words = line.split()
    if len(words) == 3:
        dic[words[1]] = words[2]

ans = []

for line in record:
    words = line.split()
    if words[0] == 'Enter':
        ans.append('{}님이 들어왔습니다.'.format(dic[words[1]]))
    elif words[0] == 'Leave':
        ans.append('{}님이 나갔습니다.'.format(dic[words[1]]))

return(ans)