package programmers.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 순위검색 {

    public int[] solution(String[] info, String[] query) {
        ArrayList<Integer>[][][][] infoArr = new ArrayList[3][2][2][2];
        for(int i=0; i<3; i++) {
            for(int j=0; j<2; j++) {
                for(int k=0; k<2; k++) {
                    for(int l=0; l<2; l++) {
                        infoArr[i][j][k][l] = new ArrayList<>();
                    }
                }
            }
        }

        for(String i : info) {
            String[] split = i.split(" ");
            int lang = 0;
            if(split[0].equals("java")) {
                lang = 1;
            } else if(split[0].equals("python")) {
                lang = 2;
            }
            int job = split[1].equals("backend") ? 0 : 1;
            int career = split[2].equals("junior") ? 0 : 1;
            int food = split[3].equals("chicken") ? 0 : 1;
            infoArr[lang][job][career][food].add(Integer.parseInt(split[4]));
        }

        int[] answer = new int[query.length];

        for(int i=0; i<3; i++) {
            for(int j=0; j<2; j++) {
                for(int k=0; k<2; k++) {
                    for(int l=0; l<2; l++) {
                        Collections.sort(infoArr[i][j][k][l]);
                    }
                }
            }
        }

        for(int i=0; i<query.length; i++) {
            String now = query[i];
            String[] split = now.split(" ");

            ArrayList<Integer> langs = new ArrayList<>();
            ArrayList<Integer> jobs = new ArrayList<>();
            ArrayList<Integer> careers = new ArrayList<>();
            ArrayList<Integer> foods = new ArrayList<>();

            String lang = split[0];
            if(lang.equals("-")) {
                langs.add(0);
                langs.add(1);
                langs.add(2);
            } else if(lang.equals("cpp")) {
                langs.add(0);
            } else if(lang.equals("java")) {
                langs.add(1);
            } else {
                langs.add(2);
            }

            String job = split[2];
            if(job.equals("-")) {
                jobs.add(0);
                jobs.add(1);
            } else if(job.equals("backend")){
                jobs.add(0);
            } else {
                jobs.add(1);
            }

            String career = split[4];
            if(career.equals("-")) {
                careers.add(0);
                careers.add(1);
            } else if(career.equals("junior")){
                careers.add(0);
            } else {
                careers.add(1);
            }

            String food = split[6];
            if(food.equals("-")) {
                foods.add(0);
                foods.add(1);
            } else if(food.equals("chicken")) {
                foods.add(0);
            } else {
                foods.add(1);
            }

            answer[i] = getCount(infoArr, langs, jobs, careers, foods, Integer.parseInt(split[7]));
        }

        return answer;
    }

    public int getCount(List<Integer>[][][][] infoArr,
                        List<Integer> langs,
                        List<Integer> jobs,
                        List<Integer> careers,
                        List<Integer> foods,
                        int score) {
        int sum = 0;

        for(int l : langs) {
            for(int j : jobs) {
                for(int c : careers) {
                    for(int f : foods) {
                        List<Integer> now = infoArr[l][j][c][f];
                        int index = Collections.binarySearch(now, score);
                        if(index < 0) {
                            index = (index + 1) * -1;
                        }
                        else {
                            int before = index - 1;
                            while(before >= 0) {
                                if(!now.get(before).equals(now.get(index))) {
                                    index = before + 1;
                                    break;
                                } else {
                                    before--;
                                }
                            }
                            if(before == -1) index = 0;
                        }
                        sum += now.size() - index;
                    }
                }
            }
        }
        return sum;
    }

}
