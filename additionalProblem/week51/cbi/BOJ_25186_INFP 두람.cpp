#include <bits/stdc++.h>
#define pii pair<int, int>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N; 
    cin >> N;

    priority_queue<pii, vector<pii>, less<pii> > pq;
    for (int i = 0; i < N; i++) {
        int d;
        cin >> d;
        pq.push({d, i});
    }

    int start = pq.top().second, tail = -1;
    while(pq.size() > 1) {
        int a = pq.top().first, a_idx = pq.top().second;
        pq.pop();
        int b = pq.top().first, b_idx = pq.top().second;;
        if(a_idx == tail) tail = a_idx;
        else tail = a_idx;
        pq.pop();
        if(a > b) pq.push({a - b, tail});
    }

    if(pq.empty() || (pq.top().first == 1 && tail != start)) {
        cout << "Happy";
    }
    else cout << "Unhappy";
}