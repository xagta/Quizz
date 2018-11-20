package com.example.bulldozer.revisonds_quizz.Entity;

/**
 * Created by xagta on 30/10/2017.
 */

public class Player {

    private String name ;
    private String score ;
    private String total ;

    public Player(String name, String score, String total) {
        this.name = name;
        this.score = score;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
