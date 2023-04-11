package algoStudy.week1;


import java.util.*;

public class Programmers42579 {
    public static int[] solution(String[] genres, int[] plays){
        // String = 장르 이름
        // int[x][y]
        // x=장르 내 순위x인 곡의 인덱스(ex)0은 1위)
        // y=장르 내 순위 x인 곡의 재생횟수
        // [2][0]은 전체 장르 재생 횟수를 의미함

        HashMap<String, int[][]> hm = new HashMap<>();
        for(int i=0;i<genres.length;i++){
            //장르가 처음 등록되는것
            if(hm.get(genres[i])==null){
                int [][]info = {{-1,-1},{-1,-1},{-1,-1}};
                //1순위에 해당 곡의 정보 넣어줌
                info[0][0]=i; info[0][1]= plays[i]; info[2][0]=plays[i];
                hm.put(genres[i],info);
                continue;
            }
            int [][]info = hm.get(genres[i]); //현 장르의 전체 재생 횟수 늘림
            info[2][0]+=plays[i];
            for(int j=0;j<2;j++){
                if(info[j][1]<plays[i]){ //같은 장르의 새로운 곡이 더 재생횟수 많음
                    //이미 등록된것보다 많을때
                    int tmpIndex = info[j][0]; //이전곡의 인덱스 저장
                    int tmpPlay = info[j][1]; //이전곡의 횟수 저장
                    info[j][0]=i; //현재 노래로 바뀜
                    info[j][1]=plays[i];
                    if(j==0){ //1등하고 자리바꿈시 2등에 이전의 1등을 넣어줌
                        info[1][0]=tmpIndex;
                        info[1][1]=tmpPlay;
                        break;
                    }
                }
            }
            hm.put(genres[i],info); //바뀐 info를 저장
        }

        //특정 장르의 전체 재생 횟수를 키값으로(유일함) <횟수,장르> 로 저장하는 해시맵
        HashMap<Integer,String> hmGenreLank = new HashMap<>();
        for (String s : hm.keySet()) {
            hmGenreLank.put(hm.get(s)[2][0],s);
        }
        //횟수를 기준으로 내림차순으로 정리(장르랭킹)
        List<Integer> playLanks = new ArrayList<>(hmGenreLank.keySet());
        playLanks.sort(Comparator.reverseOrder());

        //정답리스트
        ArrayList<Integer> ansList = new ArrayList<>();

        //1순위부터 각 장르의 1,2등 저장
        for (Integer playLank : playLanks) {
            for(int i=0;i<2;i++){
                if(hm.get(hmGenreLank.get(playLank))[i][1]!=-1){
                    ansList.add(hm.get(hmGenreLank.get(playLank))[i][0]);
                }
            }
        }


        int[] ans = new int[ansList.size()];
        int size = 0;
        for(Integer integer:ansList){
            ans[size++] = integer;
        }
        return ans;
    }

    public static void main(String[] args) {
        String genres[]={"classic", "Newage", "pop", "classic", "classic", "pop", "Newage"};
        int plays[] = {500, 1700, 600, 150, 800, 2500, 1500};
        System.out.println(Arrays.toString(solution(genres,plays)));


    }


}
