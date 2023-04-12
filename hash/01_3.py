# 백준
import sys
import collections

n = int(sys.stdin.readline())
myDict = {}
for i in range(0, n):
    person, log = sys.stdin.readline().split()
    myDict[person] = log

myDict = dict(collections.OrderedDict(sorted(myDict.items(), reverse=True)))

answer = [k for k, v in myDict.items() if v == 'enter']
for i in answer:
    print(i)
