
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int cur, s, e, ans;

int solution(vector<vector<int>> targets) {
    
    sort(targets.begin(), targets.end(), [](vector<int> a, vector<int> b) {
        return a[1] < b[1];
    });
    
    
    for(int i = 0; i < targets.size(); i++) {
        s = targets[i][0];
        e = targets[i][1];
        
        if (cur <= s) {
            ans++;
            cur = e;
        }
    }
    return ans;
}