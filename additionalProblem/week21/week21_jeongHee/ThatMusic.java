package additional2;

import java.util.PriorityQueue;

public class ThatMusic {
    //https://school.programmers.co.kr/learn/courses/30/lessons/17683
    //방금 그곡
    static PriorityQueue<Music> pq = new PriorityQueue<>();

    static class Music implements Comparable<Music> {
        int index;
        String start;
        String end;
        String title;
        String code;
        int playTime;

        public Music(int index, String start, String end, String title, String code) {
            this.index = index;
            this.start = start;
            this.end = end;
            this.title = title;
            this.code = code;
            this.playTime = getPlayTIme(start, end);
        }

        //플레이시간 내림차순, 인덱스 오름차순
        @Override
        public int compareTo(Music o) {
            if (playTime == o.playTime)
                return index - o.index;
            return o.playTime - playTime;
        }
    }

    //C# -> c 로 표현
    static String convertCode(String code) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '#') continue;
            if (i != code.length() - 1 && code.charAt(i + 1) == '#') {
                sb.append((char) (code.charAt(i) + 32));
                continue;
            }
            sb.append(code.charAt(i));
        }
        return sb.toString();
    }

    //플레이 시간만큼 플레이된 코드를 반환함
    static String getPlayedCode(int playTime, String code) {
        int loop = playTime / code.length();
        StringBuilder sb = new StringBuilder();
        while (loop-- > 0) {
            sb.append(code);
        }
        sb.append(code.substring(0, playTime % code.length()));
        return sb.toString();
    }

    //플레이된 시간을 반환함
    static int getPlayTIme(String start, String end) {
        String[] s = start.split(":");
        String[] e = end.split(":");
        return Integer.parseInt(e[0]) * 60 + Integer.parseInt(e[1])
                - (Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]));
    }

    public String solution(String m, String[] musicinfos) {
        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");
            pq.add(new Music(i, info[0], info[1], info[2],
                    getPlayedCode(getPlayTIme(info[0], info[1]), convertCode(info[3]))));
        }
        String target = convertCode(m);
        while (!pq.isEmpty()){
            Music music = pq.poll();
            if(music.code.contains(target)){
                return music.title;
            }
        }
        return "(None)";

    }

    public static void main(String[] args) {
        ThatMusic t = new ThatMusic();
        t.solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"});
    }
}
