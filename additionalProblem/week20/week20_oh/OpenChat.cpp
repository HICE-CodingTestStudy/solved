
#include <string>
#include <map>
#include <vector>
using namespace std;

string tmp, uid, name;
map<string, string> m;

void strToken(string& s) {
    tmp = s.substr(s.find(" ") + 1, s.length());
    uid = tmp.substr(0, tmp.find(" "));
    name = tmp.substr(tmp.find(" ") + 1, tmp.length());
}

vector<string> solution(vector<string> record) {
    vector<string> answer;
    
    for (int i = 0; i < record.size(); i++) {
        if (record[i][0] == 'L') continue;
        strToken(record[i]);
        m[uid] = name;
    }
    
    for (int i = 0; i < record.size(); i++) {
        strToken(record[i]);
        if (record[i][0] == 'E')
            answer.push_back(m[uid] + "님이 들어왔습니다.");
        else if (record[i][0] == 'L')
            answer.push_back(m[uid] + "님이 나갔습니다.");
    }
    return answer;
}
