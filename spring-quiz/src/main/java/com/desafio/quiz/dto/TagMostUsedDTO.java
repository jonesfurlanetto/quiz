package com.desafio.quiz.dto;

import com.desafio.quiz.model.Tag;

public class TagMostUsedDTO {

    private int totalUsed;
    private Tag tag;

    public int getTotalUsed() {
        return totalUsed;
    }

    public void setTotalUsed(int totalUsed) {
        this.totalUsed = totalUsed;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
