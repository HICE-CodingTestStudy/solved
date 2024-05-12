
#include <string>
#include <vector>
#include <map>
using namespace std;

int solution(vector<string> friends, vector<string> gifts) {
    int ans = 0;
    int n = friends.size();
    map<string,int> num;
    map<int,map<int,int>> m;
    map<int,int> received;
    map<int,int> sent;

    for(int i = 0; i < n; i++)
        num[friends[i]] = i;

    for (string g: gifts) {
        int pos = g.find_first_of(' ');
        int a = num[g.substr(0, pos)];
        int b = num[g.substr(pos + 1)];
        m[a][b]++;
        received[b]++;
        sent[a]++;
    }

    map<int,int> nextR;
    for(int a = 0; a < n; a++)
        for(int b = a + 1; b < n; b++) {
            if(m[a][b] + m[b][a] > 0 && m[a][b] != m[b][a]) {
                if(m[a][b] > m[b][a]) nextR[a]++;
                else nextR[b]++;
                continue;
            }
            int A = sent[a] - received[a];
            int B = sent[b] - received[b];
            if (A == B) continue;
            if (A > B) nextR[a]++;
            else nextR[b]++;
        }

    for (auto& it: nextR)
        if (it.second > ans) ans = it.second;

    return ans;
}