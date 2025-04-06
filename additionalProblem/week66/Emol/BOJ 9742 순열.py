# BOJ 9742 순열
import sys, math

def nth_permutation(s, pos):
    n = len(s)
    total = math.factorial(n)
    if pos > total:
        return "No permutation"

    pos -= 1  
    elements = list(s)
    result = []
    
    for i in range(n, 0, -1):
        fact = math.factorial(i - 1)
        index = pos // fact
        result.append(elements[index])
        del elements[index]
        pos %= fact
    return "".join(result)

def main():
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue

        parts = line.split()
        s = parts[0]
        pos = int(parts[1])
        result = nth_permutation(s, pos)
        print(f"{s} {parts[1]} = {result}")

if __name__ == '__main__':
    main()
