
#include <string>
#include <vector>
#include <stack>

using namespace std;

stack<int> d;
stack<int> p;

long long solution(int cap, int n, vector<int> deliveries, vector<int> pickups) {

    long long ans = 0;
    
    for(int i=0;i<n;i++){
        d.push(deliveries[i]);
        p.push(pickups[i]);
    }
    
    while(!d.empty() || !p.empty()){
        int dist = max(d.size(), p.size());
        
        int a = 0;
        while(!d.empty() && a <= cap){
            a += d.top();
            d.pop();
        }
        if(a > cap) d.push(a - cap);
        
        int b = 0;
        while(!p.empty() && b <= cap){
            b += p.top();
            p.pop();
        }
        if(b > cap) p.push(b - cap);
        
        if(!a && !b) break;
        
        ans += 2 * dist;
    }
    
    return ans;
}