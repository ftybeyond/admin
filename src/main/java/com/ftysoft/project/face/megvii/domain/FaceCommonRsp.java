package com.ftysoft.project.face.megvii.domain;

public class FaceCommonRsp {
    String result;

    String error;

    public FaceCommonRsp(String result, String error) {
        this.result = result;
        this.error = error;
    }

    public FaceCommonRsp() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
