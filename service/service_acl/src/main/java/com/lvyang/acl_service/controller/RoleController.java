package com.lvyang.acl_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.acl_service.entity.Role;
import com.lvyang.acl_service.entity.RolePermission;
import com.lvyang.acl_service.entity.UserRole;
import com.lvyang.acl_service.service.RolePermissionService;
import com.lvyang.acl_service.service.RoleService;
import com.lvyang.acl_service.service.UserRoleService;
import com.lvyang.common_utils.JsonResultUnity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/role")
//@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public JsonResultUnity index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            Role role) {
        Page<Role> pageParam = new Page<>(page, limit);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(role.getRoleName())) {
            wrapper.like("role_name",role.getRoleName());
        }
        roleService.page(pageParam,wrapper);
        return JsonResultUnity.correct().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("get/{id}")
    public JsonResultUnity get(@PathVariable String id) {
        Role role = roleService.getById(id);
        return JsonResultUnity.correct().data("item", role);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public JsonResultUnity save(@RequestBody Role role) {
        roleService.save(role);
        return JsonResultUnity.correct();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public JsonResultUnity updateById(@RequestBody Role role) {
        roleService.updateById(role);
        return JsonResultUnity.correct();
    }

  @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{id}")
    public JsonResultUnity remove(@PathVariable String id) {
        //删除当前角色权限数据
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",id);
        rolePermissionService.remove(wrapper);
        //删除角色和用户得关系记录
        QueryWrapper<UserRole> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("role_id",id);
        userRoleService.remove(roleQueryWrapper);
        //删除角色
        roleService.removeById(id);
        return JsonResultUnity.correct();
    }

    @ApiOperation(value = "根据id列表删除角色")
    @DeleteMapping("batchRemove")
    public JsonResultUnity batchRemove(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return JsonResultUnity.correct();
    }
}

