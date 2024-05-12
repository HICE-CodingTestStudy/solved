
#include <queue>
#include <vector>

using namespace std;

long long solution(int n, vector<int> works) {
    long long ans = 0;
    priority_queue<int> pq;
    
    for (auto k: works) pq.push(k);
    
    while (n-- && !pq.empty()) {
        int x = pq.top();
        pq.pop();
        if (x - 1)
            pq.push(x - 1);
    }
    
    while (!pq.empty()) {
        ans += pq.top() * pq.top();
        pq.pop();
    }
    return ans;
}
