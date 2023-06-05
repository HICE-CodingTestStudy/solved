package greedy;

public class JoyStick {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42860
    //조이스틱
    //그리디 풀이 실패
    public int solution(String name) {
        int[] checkDone = new int[name.length()];
        int notA = 0;
        int answer = 0;
        for (int i = 0; i < name.length(); i++) {
            if(name.charAt(i)=='A'){
                checkDone[i]=1;
                continue;
            }
            checkDone[i]=0;
            notA++;
        }
        int current = 0;
        if(checkDone[0]==0){
            int count = name.charAt(current)-'A';
            if(count<13)
                answer+=count;
            else answer+=26-count;
            checkDone[current]=1;
            notA--;
        }
        while (notA!=0){
            int left=0,right=0;
            while (true){
                left++;
                if(current-left<0&&checkDone[name.length()+current-left]==0)break;
                if(current-left>=0&&checkDone[current-left]==0) break;
            }
            while (true){
                right++;
                if(current+right<name.length()&&checkDone[current+right]==0) break;
                if(current+right>=name.length()&&checkDone[current+right-name.length()]==0) break;
            }

            if(right>=left){
                answer+=left;
                if(current-left>=0) current=current-left;
                else current=name.length()+current-left;
            }
            if(right<left){
                answer+=right;
                if(current+right<name.length()) current=current+right;
                else current=current+right-name.length();
            }

            int sameCount = name.charAt(current)-'A';
            if(sameCount<13)
                answer+=sameCount;
            else answer+=26-sameCount;
            checkDone[current]=1;
            notA--;
        }
        return answer;

    }

    public static void main(String[] args) {
        JoyStick j = new JoyStick();
        System.out.println(j.solution("BBBBAAAABA"));
    }

}
