package com.ftysoft.project.face.megvii.domain;

public class FaceDetectRsp {

    String reqid;

    Integer time_used;

    String delta_validation;

    Face[] faces;

    String error;

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public Integer getTime_used() {
        return time_used;
    }

    public void setTime_used(Integer time_used) {
        this.time_used = time_used;
    }

    public String getDelta_validation() {
        return delta_validation;
    }

    public void setDelta_validation(String delta_validation) {
        this.delta_validation = delta_validation;
    }

    public Face[] getFaces() {
        return faces;
    }

    public void setFaces(Face[] faces) {
        this.faces = faces;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

