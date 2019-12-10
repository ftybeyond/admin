package com.ftysoft.project.face.user.service.impl;

import java.util.List;

import com.ftysoft.project.face.megvii.component.FaceHttpUtil;
import com.ftysoft.project.face.megvii.domain.FaceCommonRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ftysoft.project.face.user.mapper.FaceUserMapper;
import com.ftysoft.project.face.user.domain.FaceUser;
import com.ftysoft.project.face.user.service.IFaceUserService;
import com.ftysoft.common.utils.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户人像信息Service业务层处理
 * 
 * @author FTY
 * @date 2019-09-27
 */
@Service
public class FaceUserServiceImpl implements IFaceUserService 
{
    @Autowired
    private FaceUserMapper faceUserMapper;

    @Autowired
    private FaceHttpUtil faceHttpUtil;

    /**
     * 查询用户人像信息
     * 
     * @param id 用户人像信息ID
     * @return 用户人像信息
     */
    @Override
    public FaceUser selectFaceUserById(Long id)
    {
        return faceUserMapper.selectFaceUserById(id);
    }

    /**
     * 查询用户人像信息列表
     * 
     * @param faceUser 用户人像信息
     * @return 用户人像信息
     */
    @Override
    public List<FaceUser> selectFaceUserList(FaceUser faceUser)
    {
        return faceUserMapper.selectFaceUserList(faceUser);
    }

    /**
     * 新增用户人像信息
     * 
     * @param faceUser 用户人像信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertFaceUser(FaceUser faceUser) throws Exception {
        int result = faceUserMapper.insertFaceUser(faceUser);
        if(faceUser.getFaceImg()!=null&&faceUser.getFaceImg().length>1){
            FaceCommonRsp commonRsp = faceHttpUtil.faceAdd(faceUser.getFaceImg(),faceUser.getFaceGroup(),faceUser.getRemark(),faceUser.getId().toString());
            if(!"true".equals(commonRsp.getResult())){
                throw new Exception("sss");
            }
        }
        return result;
    }

    /**
     * 修改用户人像信息
     * 
     * @param faceUser 用户人像信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateFaceUser(FaceUser faceUser) throws Exception {
        int result = faceUserMapper.updateFaceUser(faceUser);
        if(faceUser.getFaceImg()!=null&&faceUser.getFaceImg().length>1){
            FaceCommonRsp commonRsp = faceHttpUtil.faceAdd(faceUser.getFaceImg(),faceUser.getFaceGroup(),faceUser.getRemark(),faceUser.getId().toString());
            if(!"true".equals(commonRsp.getResult())){
                throw new Exception("sss");
            }
        }
        return result;
    }

    /**
     * 删除用户人像信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFaceUserByIds(String ids)
    {
        for(String id:ids.split(",")){
            FaceUser faceUser = faceUserMapper.selectFaceUserById(Long.parseLong(id));
            faceHttpUtil.faceDelete(faceUser.getFaceGroup(),faceUser.getId().toString());
        }
        return faceUserMapper.deleteFaceUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户人像信息信息
     * 
     * @param id 用户人像信息ID
     * @return 结果
     */
    public int deleteFaceUserById(Long id)
    {
        FaceUser faceUser = faceUserMapper.selectFaceUserById(id);
        faceHttpUtil.faceDelete(faceUser.getFaceGroup(),faceUser.getId().toString());
        return faceUserMapper.deleteFaceUserById(id);
    }
}
