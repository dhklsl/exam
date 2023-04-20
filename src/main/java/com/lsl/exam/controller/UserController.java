package com.lsl.exam.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.exam.entity.TabUser;
import com.lsl.exam.entity.backresult.ResultVO;
import com.lsl.exam.service.ITabRoleService;
import com.lsl.exam.service.IUserService;
import com.lsl.exam.utils.Base64Util;
import com.lsl.exam.utils.JwtUtil;
import com.lsl.exam.utils.ResultVoUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exam")
public class UserController {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger("UserController");

    @Autowired
    IUserService userService;

    @Autowired
    ITabRoleService roleService;

    @PostMapping(value = "login",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultVO<?> login(@RequestBody Map params){
        Map reuslt = new HashMap();

        try {
            String account = params.get("account") == null ? "" : params.get("account").toString();
            String pwd = params.get("pwd") == null ? "" : params.get("pwd").toString();

            if ("".equals(account) || "".equals(pwd)){
                return ResultVoUtil.error(30000,"用户名或者密码不能为空!");
            }

            //pwd解密
            String decodePwd = Base64Util.decode(pwd);
            if ("".contains(decodePwd)){
                return ResultVoUtil.error(30000,"密码错误!");
            }

            TabUser user = userService.getOne(new QueryWrapper<TabUser>()
                    .eq("account",account)
                    .eq("userpwd",decodePwd));
            if (null == user){
                return ResultVoUtil.error(30000,"用户名或者密码错误");
            }

            //获取当前用户拥有的角色
            String userId = user.getId();
            Map roleMap = new HashMap();
            roleMap.put("userId",userId);
            List<Map> roleList = roleService.qryRoleInfoByUserId(roleMap);
            List<String> roleNames = new ArrayList<>();
            for(Map role : roleList){
                roleNames.add(role.get("role").toString());
            }
            user.setRoleName(JSON.toJSONString(roleNames));

            //生成带有业务信息的jwt串
            String jwt = JwtUtil.creatJwt(user);

            //把jwt和当前用户信息返给前端
            reuslt.put("jwt",jwt);
            reuslt.put("roleNames",roleNames);
            reuslt.put("username",user.getUsername());
            reuslt.put("account",user.getAccount());
        } catch (Exception e) {
            LOG.info("UserController--login--catch--{}",e);
            return ResultVoUtil.error(30000,"登录失败");
        }

        return ResultVoUtil.success(reuslt);
    }

    @PostMapping(value = "qryUser",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultVO<?> qryUser(HttpServletRequest request){

        //这里header中的信息是filter中放进去的
        String account = request.getHeader("account");
        String username = request.getHeader("username");
        String rolename = request.getHeader("rolename");

        List<TabUser> list = null;
        try {
            list = userService.list();
        } catch (Exception e) {
            LOG.info("UserController--qryUser--catch--{}",e);
            return ResultVoUtil.error(30000,"查询用户列表异常");
        }
        return ResultVoUtil.success(list);
    }
}
