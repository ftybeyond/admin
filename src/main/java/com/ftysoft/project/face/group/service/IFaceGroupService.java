package com.ftysoft.project.face.group.service;

import com.ftysoft.project.face.group.domain.FaceGroup;

import java.util.List;

/**
 * 人像库信息Service接口
 * 
 * @author FTY
 * @date 2019-09-27
 */
public interface IFaceGroupService 
{
    /**
     * 查询人像库信息
     * 
     * @param id 人像库信息ID
     * @return 人像库信息
     */
    public FaceGroup selectFaceGroupById(Long id);

    /**
     * 查询人像库信息列表
     * 
     * @param faceGroup 人像库信息
     * @return 人像库信息集合
     */
    public List<FaceGroup> selectFaceGroupList(FaceGroup faceGroup);

    /**
     * 新增人像库信息
     * 
     * @param faceGroup 人像库信息
     * @return 结果
     */
    public int insertFaceGroup(FaceGroup faceGroup);

    /**
     * 修改人像库信息
     * 
     * @param faceGroup 人像库信息
     * @return 结果
     */
    public int updateFaceGroup(FaceGroup faceGroup);

    /**
     * 批量删除人像库信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFaceGroupByIds(String ids);

    /**
     * 删除人像库信息信息
     * 
     * @param id 人像库信息ID
     * @return 结果
     */
    public int deleteFaceGroupById(Long id);
}
