package com.ftysoft.project.face.megvii.domain;

public class FmpRsp {

    String result;

    String transaction_id;

    String message;

    String verify_result;

    String score_graphics;

    String score_mask;

    String score_replay;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVerify_result() {
        return verify_result;
    }

    public void setVerify_result(String verify_result) {
        this.verify_result = verify_result;
    }

    public String getScore_graphics() {
        return score_graphics;
    }

    public void setScore_graphics(String score_graphics) {
        this.score_graphics = score_graphics;
    }

    public String getScore_mask() {
        return score_mask;
    }

    public void setScore_mask(String score_mask) {
        this.score_mask = score_mask;
    }

    public String getScore_replay() {
        return score_replay;
    }

    public void setScore_replay(String score_replay) {
        this.score_replay = score_replay;
    }
}
