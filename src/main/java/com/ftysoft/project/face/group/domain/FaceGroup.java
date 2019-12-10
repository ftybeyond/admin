package com.ftysoft.project.face.group.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ftysoft.framework.aspectj.lang.annotation.Excel;
import com.ftysoft.framework.web.domain.BaseEntity;

/**
 * 人像库信息对象 face_group
 * 
 * @author FTY
 * @date 2019-09-27
 */
public class FaceGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 组名 */
    @Excel(name = "组名")
    private String name;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("remark", getRemark())
            .toString();
    }
}
