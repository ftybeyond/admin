package com.ftysoft.project.face.group.controller;

import java.util.List;

import com.ftysoft.project.face.group.domain.FaceGroup;
import com.ftysoft.project.face.group.service.IFaceGroupService;
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
import com.ftysoft.framework.web.controller.BaseController;
import com.ftysoft.framework.web.domain.AjaxResult;
import com.ftysoft.common.utils.poi.ExcelUtil;
import com.ftysoft.framework.web.page.TableDataInfo;

/**
 * 人像库信息Controller
 * 
 * @author FTY
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/face/group")
public class FaceGroupController extends BaseController
{
    private String prefix = "face/group";

    @Autowired
    private IFaceGroupService faceGroupService;

    @RequiresPermissions("face:group:view")
    @GetMapping()
    public String group()
    {
        return prefix + "/group";
    }

    /**
     * 查询人像库信息列表
     */
    @RequiresPermissions("face:group:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FaceGroup faceGroup)
    {
        startPage();
        List<FaceGroup> list = faceGroupService.selectFaceGroupList(faceGroup);
        return getDataTable(list);
    }

    /**
     * 导出人像库信息列表
     */
    @RequiresPermissions("face:group:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FaceGroup faceGroup)
    {
        List<FaceGroup> list = faceGroupService.selectFaceGroupList(faceGroup);
        ExcelUtil<FaceGroup> util = new ExcelUtil<FaceGroup>(FaceGroup.class);
        return util.exportExcel(list, "group");
    }

    /**
     * 新增人像库信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存人像库信息
     */
    @RequiresPermissions("face:group:add")
    @Log(title = "人像库信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FaceGroup faceGroup)
    {
        return toAjax(faceGroupService.insertFaceGroup(faceGroup));
    }

    /**
     * 修改人像库信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FaceGroup faceGroup = faceGroupService.selectFaceGroupById(id);
        mmap.put("faceGroup", faceGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存人像库信息
     */
    @RequiresPermissions("face:group:edit")
    @Log(title = "人像库信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FaceGroup faceGroup)
    {
        return toAjax(faceGroupService.updateFaceGroup(faceGroup));
    }

    /**
     * 删除人像库信息
     */
    @RequiresPermissions("face:group:remove")
    @Log(title = "人像库信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(faceGroupService.deleteFaceGroupByIds(ids));
    }
}
