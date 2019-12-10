package com.ftysoft.project.face.user.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ftysoft.framework.aspectj.lang.annotation.Excel;
import com.ftysoft.framework.web.domain.BaseEntity;

/**
 * 用户人像信息对象 face_user
 * 
 * @author FTY
 * @date 2019-09-27
 */
public class FaceUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 用户标识 */
    @Excel(name = "用户标识")
    private Long userId;

    /** 归属人像库 */
    @Excel(name = "归属人像库")
    private String faceGroup;

    /** 人像照片 */
    private byte[] faceImg;

    /** 人脸特征值 */
    private String faceFeature;

    /** 特征值标记名称 */
    @Excel(name = "特征值标记名称")
    private String tag;

    /** 工号 */
    @Excel(name = "工号")
    private String workNum;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setFaceGroup(String faceGroup) 
    {
        this.faceGroup = faceGroup;
    }

    public String getFaceGroup()
    {
        return faceGroup;
    }
    public void setFaceImg(byte[] faceImg)
    {
        this.faceImg = faceImg;
    }

    public byte[] getFaceImg()
    {
        return faceImg;
    }
    public void setFaceFeature(String faceFeature) 
    {
        this.faceFeature = faceFeature;
    }

    public String getFaceFeature() 
    {
        return faceFeature;
    }
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public String getTag() 
    {
        return tag;
    }
    public void setWorkNum(String workNum) 
    {
        this.workNum = workNum;
    }

    public String getWorkNum() 
    {
        return workNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("faceGroup", getFaceGroup())
            .append("faceImg", getFaceImg())
            .append("faceFeature", getFaceFeature())
            .append("tag", getTag())
            .append("workNum", getWorkNum())
            .toString();
    }
}
