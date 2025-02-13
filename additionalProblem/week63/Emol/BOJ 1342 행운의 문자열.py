# BOJ 1342 행운의 문자열
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    s = input().strip()

    letters = sorted(set(s))
    n = len(letters)
    count_list = [s.count(ch) for ch in letters]
    
    dp = {}
    
    def dfs(last, counts):

        if sum(counts) == 0:
            return 1


        key = (last, counts)
        if key in dp:
            return dp[key]
        
        total = 0
        for i in range(n):

            if counts[i] > 0 and i != last:
                new_counts = list(counts)
                new_counts[i] -= 1
                total += dfs(i, tuple(new_counts))
        
        dp[key] = total
        return total

    result = dfs(-1, tuple(count_list))
    print(str(result))
