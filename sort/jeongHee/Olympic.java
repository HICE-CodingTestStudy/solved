package sort;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Olympic {
    //https://www.acmicpc.net/problem/8979
    //올림픽

    public static class Country {
        private int index=0;
        private int gold=0;
        private int silver = 0;
        private int bronze = 0;
        private int rank = 0;

        public Country(int index, int gold, int silver, int bronze) {
            this.index = index;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        public int getGold() {
            return gold;
        }

        public int getSilver() {
            return silver;
        }

        public int getBronze() {
            return bronze;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }


        public int getIndex() {
            return index;
        }


        public boolean isSame(Country c){
            return this.getGold() == c.getGold()
                    && this.getSilver() == c.getSilver()
                    && this.getBronze() == c.getBronze();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        PriorityQueue<Country> countries = new PriorityQueue<Country>(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                if (o1.getGold()!=o2.getGold())
                    return -o1.getGold()+o2.getGold();
                if(o1.getSilver()!=o2.getSilver())
                    return -o1.getSilver()+o2.getSilver();
                if(o1.getBronze()!=o2.getBronze())
                    return -o1.getBronze()+o2.getBronze();
                return 0;
            }
        });
        for (int i = 0; i < N; i++) {
            Country country = new Country(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt());
            countries.add(country);
        }
        int index = 1;
        Country before=null;
        while (!countries.isEmpty()){
            Country thisCountry = countries.poll();
            if(before!=null&&before.isSame(thisCountry)){
                thisCountry.setRank(before.getRank());
            }
            else{
                thisCountry.setRank(index);
            }
            index++;
            before=thisCountry;
            if(thisCountry.getIndex()==K){
                System.out.println(thisCountry.getRank());
                return;
            }
        }
    }
}
