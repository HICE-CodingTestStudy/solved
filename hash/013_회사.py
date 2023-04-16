import sys
import collections

n = int(sys.stdin.readline())
myDict = {}

# {"이름": "로그"} 꼴로 dict에 저장.
for i in range(0, n):
    person, log = sys.stdin.readline().split()
    myDict[person] = log

# 이름을 사전 역순으로 정렬
# collections.OrderedDict(): dict순서가 임의로 바뀌지 않게 보장. (불필요해보임)
myDict = dict(collections.OrderedDict(sorted(myDict.items(), reverse=True)))

# 로그 == "enter"인 이름 출력
answer = [k for k, v in myDict.items() if v == 'enter']
for i in answer:
    print(i)
