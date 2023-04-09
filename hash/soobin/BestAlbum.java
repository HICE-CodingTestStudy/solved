package soobin;

import java.util.*;
import java.util.stream.Collectors;

public class BestAlbum {
    public static List<Integer> solution(String[] genres, int[] plays) {
        HashMap<String, Integer> streamsOfGenre = new HashMap<>();
        HashMap<String, Object> songsOfGenre = new HashMap<>();
        List<Integer> answer = new ArrayList<>();

        for(int i = 0; i < genres.length; i++) {
            streamsOfGenre.put(genres[i], streamsOfGenre.getOrDefault(genres[i], 0) + plays[i]);
            ((HashMap<Integer, Integer>) songsOfGenre
                    .computeIfAbsent(genres[i], songs -> new HashMap<>())).put(i, plays[i]);
        }

        List<Map.Entry<String, Integer>> sortedStream = streamsOfGenre.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        for(Map.Entry<String, Object> entry : songsOfGenre.entrySet()) {
            List<Map.Entry<Integer, Integer>> sortedSongs = ((HashMap<Integer, Integer>) entry.getValue())
                    .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toList());
            songsOfGenre.put(entry.getKey(), sortedSongs);
        }

        for(Map.Entry genre : sortedStream) {
            Iterator<Map.Entry<Integer, Integer>> iterator =
                    ((List<Map.Entry<Integer, Integer>>)songsOfGenre.get(genre.getKey())).iterator();
            for(int i = 0; i < 2 && iterator.hasNext(); i++)
                answer.add(iterator.next().getKey());
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "alternative", "pop", "classic", "pop", "rock"};
        int[] plays = {500, 600, 500, 800, 2500, 1000, 250, 400};
        System.out.println(solution(genres, plays));
    }
}
