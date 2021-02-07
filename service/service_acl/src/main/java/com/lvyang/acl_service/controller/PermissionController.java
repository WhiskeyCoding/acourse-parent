package com.lvyang.acl_service.controller;


import com.lvyang.acl_service.entity.Permission;
import com.lvyang.acl_service.service.PermissionService;

import com.lvyang.common_utils.JsonResultUnity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public JsonResultUnity indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        System.out.println(list);
        return JsonResultUnity.correct().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public JsonResultUnity remove(@PathVariable String id) {
        permissionService.removeChildById(id);
        return JsonResultUnity.correct();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public JsonResultUnity doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return JsonResultUnity.correct();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public JsonResultUnity toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return JsonResultUnity.correct().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public JsonResultUnity save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return JsonResultUnity.correct();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public JsonResultUnity updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return JsonResultUnity.correct();
    }

}

