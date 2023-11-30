
#include <string>
#include <vector>
using namespace std;

int d, maxDiff, arr[11];
vector<int> ans = { -1 };

int cmp(vector<int> ryan, vector<int> apeach) {
    int rs = 0, as = 0;
    for (int i = 0; i < 11; i++)
        if (ryan[i] > apeach[i])
            rs += 10 - i;
        else if (apeach[i]) 
            as += 10 - i;
    
    return rs - as;
}

void dfs(int depth, int idx, vector<int> ryan, vector<int> apeach) {
    if (depth == d) {
        for (int i = 0; i < d; i++)
            ryan[10 - arr[i]]++;
            
        int diff = cmp(ryan, apeach);

        if (diff > maxDiff) {
            maxDiff = diff;
            ans = ryan;
        }
        return;
    }

    for (int i = idx; i < 11; i++) {
        arr[depth] = i;
        dfs(depth + 1, i, ryan, apeach);
    }
}

vector<int> solution(int n, vector<int> info) {
    vector<int> ryan(11, 0);
    vector<int> apeach = info;
    d = n;
    
    dfs(0, 0, ryan, apeach);

    return ans;
}
