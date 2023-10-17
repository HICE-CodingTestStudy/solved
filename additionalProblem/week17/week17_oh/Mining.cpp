#include <string>
#include <vector>
#include <queue>
#include <iostream>
using namespace std;

priority_queue<pair<int, int>> pq;

int solution(vector<int> picks, vector<string> minerals) {
    int ans = 0, cnt = 1, sum = 0;
    for (int i = 0; i < minerals.size(); i++) {
        if(minerals[i][0] == 'd')
            sum += 100;
        else if(minerals[i][0] == 'i')
            sum += 10;
        else sum += 1;
        
        if (i % 5 == 4) {
            pq.push({sum, cnt});
            sum = 0;
            cnt++;
        }
    }
    
    if (minerals.size() % 5)
        pq.push({sum, cnt});
    
    int d = picks[0], i = picks[1], s = picks[2];
    int total = d + i + s;
    
    while (d--) {
        if (pq.empty()) return ans;
        int t = pq.top().first;
        int num = pq.top().second;
        pq.pop();
        if (total < num) {d++; continue;}
        ans += t / 100; t %= 100;
        ans += t / 10; t %= 10;
        ans += t;
    }
    
    while (i--) {
        if (pq.empty()) return ans;
        int t = pq.top().first;
        int num = pq.top().second;
        pq.pop();
        if (total < num) {i++; continue;}
        ans += t / 100 * 5; t %= 100;
        ans += t / 10; t %= 10;
        ans += t;
    }
    
    while (s--) {
        if (pq.empty()) return ans;
        int t = pq.top().first;
        int num = pq.top().second;
        pq.pop();
        if (total < num) {s++; continue;}
        ans += t / 100 * 25; t %= 100;
        ans += t / 10 * 5; t %= 10;
        ans += t;
    }
    
    return ans;
}
