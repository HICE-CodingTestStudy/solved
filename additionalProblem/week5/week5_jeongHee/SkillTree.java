package queue;

import java.util.HashMap;

public class SkillTree {
    //https://school.programmers.co.kr/learn/courses/30/lessons/49993
    //스킬 트리
    public int solution(String skill, String[] skill_trees) {
        int count = 0;
        HashMap<Character, Character> previousSkill = new HashMap<>();
        for (int i = 0; i < skill.length(); i++) {
            if (i == 0) {
                previousSkill.put(skill.charAt(i), '0');
                continue;
            }
            previousSkill.put(skill.charAt(i), skill.charAt(i - 1));
        }
        for (int i = 0; i < skill_trees.length; i++) {
            HashMap<Character, Boolean> isLearned = new HashMap<>();
            String skillTree = skill_trees[i];
            boolean canLearn = true;
            for (int j = 0; j < skillTree.length(); j++) {
                if(previousSkill.get(skillTree.charAt(j))==null) continue;
                char previous = previousSkill.get(skillTree.charAt(j));
                if (previous == '0') {
                    isLearned.put(skillTree.charAt(j), true);
                    continue;
                }
                if (isLearned.get(previous) == null) {
                    canLearn = false;
                    break;
                }
                isLearned.put(skillTree.charAt(j), true);
            }
            if (canLearn) count++;
        }
        return count;
    }

}
