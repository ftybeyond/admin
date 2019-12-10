package com.ftysoft.project.face.megvii.domain;

public class Face{

    Float Confidence;

    String FaceRect;

    String Landmarks;

    String Feature;

    public Float getConfidence() {
        return Confidence;
    }

    public void setConfidence(Float confidence) {
        Confidence = confidence;
    }

    public String getFaceRect() {
        return FaceRect;
    }

    public void setFaceRect(String faceRect) {
        FaceRect = faceRect;
    }

    public String getLandmarks() {
        return Landmarks;
    }

    public void setLandmarks(String landmarks) {
        Landmarks = landmarks;
    }

    public String getFeature() {
        return Feature;
    }

    public void setFeature(String feature) {
        Feature = feature;
    }
}
