package com.ftysoft.project.face.user.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ftysoft.framework.aspectj.lang.annotation.Log;
import com.ftysoft.framework.aspectj.lang.enums.BusinessType;
import com.ftysoft.project.face.user.domain.FaceUser;
import com.ftysoft.project.face.user.service.IFaceUserService;
import com.ftysoft.framework.web.controller.BaseController;
import com.ftysoft.framework.web.domain.AjaxResult;
import com.ftysoft.common.utils.poi.ExcelUtil;
import com.ftysoft.framework.web.page.TableDataInfo;

/**
 * 用户人像信息Controller
 * 
 * @author FTY
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/face/user")
public class FaceUserController extends BaseController
{
    private String prefix = "face/user";

    @Autowired
    private IFaceUserService faceUserService;

    @RequiresPermissions("face:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询用户人像信息列表
     */
    @RequiresPermissions("face:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FaceUser faceUser)
    {
        startPage();
        List<FaceUser> list = faceUserService.selectFaceUserList(faceUser);
        return getDataTable(list);
    }

    /**
     * 导出用户人像信息列表
     */
    @RequiresPermissions("face:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FaceUser faceUser)
    {
        List<FaceUser> list = faceUserService.selectFaceUserList(faceUser);
        ExcelUtil<FaceUser> util = new ExcelUtil<FaceUser>(FaceUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增用户人像信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户人像信息
     */
    @RequiresPermissions("face:user:add")
    @Log(title = "用户人像信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FaceUser faceUser) throws Exception {
        return toAjax(faceUserService.insertFaceUser(faceUser));
    }

    /**
     * 修改用户人像信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FaceUser faceUser = faceUserService.selectFaceUserById(id);
        mmap.put("faceUser", faceUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户人像信息
     */
    @RequiresPermissions("face:user:edit")
    @Log(title = "用户人像信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FaceUser faceUser) throws Exception {
        return toAjax(faceUserService.updateFaceUser(faceUser));
    }

    /**
     * 删除用户人像信息
     */
    @RequiresPermissions("face:user:remove")
    @Log(title = "用户人像信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(faceUserService.deleteFaceUserByIds(ids));
    }
}
