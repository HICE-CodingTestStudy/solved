
#include <string>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

int maxNode, from, to;

struct nodeDeg {
    int out = 0, in = 0;
};

vector<int> solution(vector<vector<int>> edges) {
    vector<int> ans(4, 0);
    map<int, nodeDeg> deg;
    
    for (auto& e: edges) {
        from = e[0], to = e[1];
        deg[from].out++;
        deg[to].in++;
        maxNode = max({maxNode, from, to});
    }
    
    for (int i = 1; i <= maxNode; i++) {
        auto d = deg[i];
        if (d.out >= 2 && !d.in) ans[0] = i;
        else if (!d.out) ans[2]++;
        else if (d.out >=2 && d.in >= 2) ans[3]++;
    }
    
    ans[1] = deg[ans[0]].out - (ans[2] + ans[3]);
    
    return ans;
}