package com.ftysoft.project.face.user.mapper;

import com.ftysoft.project.face.user.domain.FaceUser;
import java.util.List;

/**
 * 用户人像信息Mapper接口
 * 
 * @author FTY
 * @date 2019-09-27
 */
public interface FaceUserMapper 
{
    /**
     * 查询用户人像信息
     * 
     * @param id 用户人像信息ID
     * @return 用户人像信息
     */
    public FaceUser selectFaceUserById(Long id);

    /**
     * 查询用户人像信息列表
     * 
     * @param faceUser 用户人像信息
     * @return 用户人像信息集合
     */
    public List<FaceUser> selectFaceUserList(FaceUser faceUser);

    /**
     * 新增用户人像信息
     * 
     * @param faceUser 用户人像信息
     * @return 结果
     */
    public int insertFaceUser(FaceUser faceUser);

    /**
     * 修改用户人像信息
     * 
     * @param faceUser 用户人像信息
     * @return 结果
     */
    public int updateFaceUser(FaceUser faceUser);

    /**
     * 删除用户人像信息
     * 
     * @param id 用户人像信息ID
     * @return 结果
     */
    public int deleteFaceUserById(Long id);

    /**
     * 批量删除用户人像信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFaceUserByIds(String[] ids);
}
