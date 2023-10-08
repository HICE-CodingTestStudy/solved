package com.example.demo.heap;

import java.util.*;

public class ParkingFee {
    //https://school.programmers.co.kr/learn/courses/30/lessons/92341
    //주차요금 계산
    static HashMap<String, Integer> totalFee = new HashMap<>(); //최종 요금 기록용
    static HashMap<String, Integer> totalTime = new HashMap<>(); //전체 이용 시간 기록용
    static HashMap<String, String> carStatus = new HashMap<>(); //입출차 상태 기록용

    private static int calculateTime(String in, String out) {
        int inHour = Integer.parseInt(in.split(":")[0]);
        int inMin = Integer.parseInt(in.split(":")[1]);
        int outHour = Integer.parseInt(out.split(":")[0]);
        int outMin = Integer.parseInt(out.split(":")[1]);
        return outHour * 60 + outMin - (inHour * 60 + inMin);
    }

    private static int calculateFee(int time, int[] fees) {
        if (time <= fees[0]) return fees[1];
        return (int) (fees[1] + Math.ceil(((double) time - fees[0]) / (double) fees[2]) * fees[3]);
    }

    public ArrayList<Integer> solution(int[] fees, String[] records) {
        for (String record : records) {
            String time = record.split(" ")[0];
            String car = record.split(" ")[1];
            String status = record.split(" ")[2];

            //입차시 해시맵에 등록
            if (status.equals("IN")) {
                carStatus.put(car, time);
                continue;
            }

            //출차시 해당 차의 입차기록을 통해 총 이용 시간 갱신
            String in = carStatus.get(car);
            carStatus.remove(car);
            if (totalTime.containsKey(car))
                totalTime.put(car, totalTime.get(car) + calculateTime(in, time));
            else totalTime.put(car, calculateTime(in, time));
        }

        //입차기록만 있는 차의 총 이용 시간 갱신
        for (String car : carStatus.keySet()) {
            if (totalTime.containsKey(car))
                totalTime.put(car, totalTime.get(car) + calculateTime(carStatus.get(car),"23:59"));
            else totalTime.put(car, calculateTime(carStatus.get(car),"23:59"));
        }

        //총 이용 시간을 통해 총 비용 계산
        for (String car : totalTime.keySet()) {
            totalFee.put(car,calculateFee(totalTime.get(car),fees));
        }

        //차 번호 기준 오름차순으로 출력
        List<String> cars = new ArrayList<>(totalFee.keySet());
        Collections.sort(cars);
        ArrayList<Integer> ans = new ArrayList<>();
        for (String car : cars) {
            ans.add(totalFee.get(car));
        }
        return ans;
    }

    public static void main(String[] args) {
        ParkingFee p = new ParkingFee();
        p.solution(new int[]{120, 0, 60, 591}, new String[]{"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"});
    }
}
