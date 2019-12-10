package com.ftysoft.project.face.megvii.domain;

public class FmpReq extends CommonAccessObj{

    String faceImg;

    String sign;

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
