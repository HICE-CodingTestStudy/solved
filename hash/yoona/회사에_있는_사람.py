from collections import defaultdict
import sys

hash_table = defaultdict(str)
n = int(sys.stdin.readline().rstrip())

for i in range(n):
    name, status = map(str, sys.stdin.readline().strip().split())
    if status == "leave":
        del hash_table[name]
    else:
        hash_table[name] = status

for name in sorted(hash_table.keys(), reverse=True):
    print(name)