package com.lvyang.edu_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.common_utils.jwtutil.JwtUtils;
import com.lvyang.common_utils.voutil.MemberInfoVO;
import com.lvyang.edu_service.entity.EduUserComment;
import com.lvyang.edu_service.nacosclient.PortalClient;
import com.lvyang.edu_service.service.EduUserCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2021-01-31
 */
@RestController
@RequestMapping("/edu_service/edu_user_comment")
@Api(tags = {"10-User Comment Management"})
//@CrossOrigin
public class EduUserCommentController {
    @Autowired
    private EduUserCommentService eduUserCommentService;
    @Autowired
    private PortalClient portalClient;

    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("pageQueryComment/{current}/{limit}")
    public JsonResultUnity pageQueryComment(@PathVariable Long current, @PathVariable Long limit, @RequestParam(required = false) String courseId) {
        Page<EduUserComment> pageParam = new Page<>(current, limit);
        QueryWrapper<EduUserComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        eduUserCommentService.page(pageParam,wrapper);
        List<EduUserComment> commentList = pageParam.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("comments", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());

        return JsonResultUnity.correct().data(map);
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("auth/comment/commentSubmit")
    public JsonResultUnity commentSubmit(@RequestBody EduUserComment eduUserComment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return JsonResultUnity.error().code(28004).message("请登录");
        }
        eduUserComment.setMemberId(memberId);

        MemberInfoVO memberInfo = portalClient.getMemberInfo(memberId);

        eduUserComment.setNickname(memberInfo.getNickname());
        eduUserComment.setAvatar(memberInfo.getAvatar());

        eduUserCommentService.save(eduUserComment);
        return JsonResultUnity.correct();
    }

}

