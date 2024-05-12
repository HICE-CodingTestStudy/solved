#include <iostream>
#include <vector>
using namespace std;

int Add (int l, int r, int w) {
    int d = r - l + 1;
    
    if (d <= 0) return 0;
    else return d / w + (d % w > 0);
}

int solution(int n, vector<int> stations, int w)
{
    int ans = 0;
    int range = 2 * w + 1;
    
    ans += Add(1, max(0, stations[0] - w - 1), range);
    
    for (int i = 0; i < stations.size() - 1; i++)
        ans += Add(stations[i] + w + 1, stations[i + 1] - w - 1, range);
    
    ans += Add(stations[stations.size() - 1] + w + 1, n, range);
    
    return ans;
}
