
#include <string>
#include <set>
#include <map>
#include <vector>

using namespace std;
int Min, Start, End;

vector<int> solution(vector<string> gems) {
    vector<int> ans(2);
    map<string, int> m;
    set<string> s(gems.begin(), gems.end());
    
    for (auto g: gems) {
        m[g]++;
        if (m.size() == s.size()) break;
        End++;
    }
    Min = End - Start;
    ans[0] = Start + 1;
    ans[1] = End + 1;
    
    while (End < gems.size()) {
        string cur = gems[Start];
        m[cur]--;
        Start++;
        
        if (!m[cur]) {
            End++;
            while (End < gems.size()) {
                m[gems[End]]++;
                if (cur == gems[End]) break;
                End++;
            }
        }
        if(End - Start < Min) {
            ans[0] = Start + 1;
            ans[1] = End + 1;
            Min = End - Start;
        }
    }
    
    return ans;
}
