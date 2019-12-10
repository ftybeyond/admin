package com.ftysoft.project.system.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ftysoft.framework.config.CommonConfig;
import com.ftysoft.framework.web.controller.BaseController;
import com.ftysoft.project.system.menu.domain.Menu;
import com.ftysoft.project.system.menu.service.IMenuService;
import com.ftysoft.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author FTY
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private CommonConfig commonConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", commonConfig.getCopyrightYear());
        mmap.put("demoEnabled", commonConfig.isDemoEnabled());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", commonConfig.getVersion());
        return "main";
    }
}
