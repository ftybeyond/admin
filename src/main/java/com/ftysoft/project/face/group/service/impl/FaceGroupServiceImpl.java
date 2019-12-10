package com.ftysoft.project.face.group.service.impl;

import java.util.List;

import com.ftysoft.project.face.group.domain.FaceGroup;
import com.ftysoft.project.face.group.mapper.FaceGroupMapper;
import com.ftysoft.project.face.group.service.IFaceGroupService;
import com.ftysoft.project.face.megvii.component.FaceHttpUtil;
import com.ftysoft.project.face.megvii.domain.FaceCommonRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ftysoft.common.utils.text.Convert;

/**
 * 人像库信息Service业务层处理
 * 
 * @author FTY
 * @date 2019-09-27
 */
@Service
public class FaceGroupServiceImpl implements IFaceGroupService
{
    @Autowired
    private FaceGroupMapper faceGroupMapper;

    @Autowired
    private FaceHttpUtil faceHttpUtil;

    /**
     * 查询人像库信息
     * 
     * @param id 人像库信息ID
     * @return 人像库信息
     */
    @Override
    public FaceGroup selectFaceGroupById(Long id)
    {
        return faceGroupMapper.selectFaceGroupById(id);
    }

    /**
     * 查询人像库信息列表
     * 
     * @param faceGroup 人像库信息
     * @return 人像库信息
     */
    @Override
    public List<FaceGroup> selectFaceGroupList(FaceGroup faceGroup)
    {
        return faceGroupMapper.selectFaceGroupList(faceGroup);
    }

    /**
     * 新增人像库信息
     * 
     * @param faceGroup 人像库信息
     * @return 结果
     */
    @Override
    public int insertFaceGroup(FaceGroup faceGroup)
    {
        FaceCommonRsp commonRsp = faceHttpUtil.faceGroupAdd(faceGroup.getName());
        if(!"true".equals(commonRsp.getResult())){
            return -1;
        }
        return faceGroupMapper.insertFaceGroup(faceGroup);
    }

    /**
     * 修改人像库信息
     * 
     * @param faceGroup 人像库信息
     * @return 结果
     */
    @Override
    public int updateFaceGroup(FaceGroup faceGroup)
    {
        return faceGroupMapper.updateFaceGroup(faceGroup);
    }

    /**
     * 删除人像库信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFaceGroupByIds(String ids)
    {
        for(String id:ids.split(",")){
            FaceCommonRsp commonRsp = faceHttpUtil.faceGroupFree(faceGroupMapper.selectFaceGroupById(Long.parseLong(id)).getName());
            if(!"true".equals(commonRsp.getResult())){
                return -1;
            }
        }
        return faceGroupMapper.deleteFaceGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除人像库信息信息
     * 
     * @param id 人像库信息ID
     * @return 结果
     */
    public int deleteFaceGroupById(Long id)
    {
        FaceCommonRsp commonRsp = faceHttpUtil.faceGroupFree(faceGroupMapper.selectFaceGroupById(id).getName());
        if(!"true".equals(commonRsp.getResult())){
            return -1;
        }
        return faceGroupMapper.deleteFaceGroupById(id);
    }
}
