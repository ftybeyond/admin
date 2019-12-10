package com.ftysoft.project.face.megvii.domain;

public class FaceSearchRsp extends FaceCommonRsp {

    Integer[] ids;

    float[] scores;

    String[] tags;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public float[] getScores() {
        return scores;
    }

    public void setScores(float[] scores) {
        this.scores = scores;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
