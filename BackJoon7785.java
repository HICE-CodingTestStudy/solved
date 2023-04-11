package algoStudy.week1;

import java.util.*;

public class BackJoon7785 {
    public static void main(String[] args) {
       HashMap<String,String> log = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        for(int i=0; i<cnt;i++){
            String name = sc.next();
            String check = sc.next();
            log.put(name,check);
        }
        List<String> sortedLog = new ArrayList<String>(log.keySet());
        sortedLog.sort(Comparator.reverseOrder());
        for (String s : sortedLog) {
            if(log.get(s).equals("enter")){
                System.out.println(s);
            }
        }

    }
}
