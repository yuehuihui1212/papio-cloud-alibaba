package com.papio.usercenter.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.papio.usercenter.domain.dto.user.JwtTokenRespDTO;
import com.papio.usercenter.domain.dto.user.LoginRespDTO;
import com.papio.usercenter.domain.dto.user.UserLoginDTO;
import com.papio.usercenter.domain.dto.user.UserRespDTO;
import com.papio.usercenter.domain.entity.user.SysUser;
import com.papio.usercenter.service.UserService;
import com.papio.utils.JwtOperator;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 14:05 2020/2/6
 * @Modified By:
 **/
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private JwtOperator jwtOperator;

    @GetMapping("findUserById/{id}")
    public ResponseEntity findUserById(@PathVariable Integer id) {
        SysUser sysUser = this.userService.findUserById(id);
        return ResponseEntity.ok(sysUser);
    }

    @GetMapping("query")
    public ResponseEntity query(SysUser sysUser) {
        return ResponseEntity.ok(sysUser);
    }

    /**
     * 登录接口
     * @param userLoginDTO
     * @return
     */
    @PostMapping("login")
    public ResponseEntity login(UserLoginDTO userLoginDTO) {
        try {
            WxMaJscode2SessionResult resuult = this.wxMaService.getUserService()
                    .getSessionInfo(userLoginDTO.getCode());
            String openid = resuult.getOpenid();
            SysUser user = this.userService.login(userLoginDTO, openid);
            Map<String,Object> userInfo=new HashMap<>(3);
            userInfo.put("id", user.getId());
            userInfo.put("wxNickName", user.getWxNickname());
            userInfo.put("role", user.getRoles());
            String token = this.jwtOperator.generateToken(userInfo);
            log.info("用户{}登录成功，生成的token = {}，有效期到:{}",
                    userLoginDTO.getWxNickName(),
                    token,
                    this.jwtOperator.getExpirationDateFromToken(token)
            );
            LoginRespDTO respDTO = LoginRespDTO.builder()
                    .user(
                            UserRespDTO.builder()
                                    .id(user.getId())
                                    .avatarUrl(user.getAvatarUrl())
                                    .bonus(user.getBonus())
                                    .wxNickName(user.getWxNickname())
                                    .build()
                    )
                    .token(
                            JwtTokenRespDTO.builder()
                                    .token(token)
                                    .expireTime(jwtOperator.getExpirationDateFromToken(token).getTime())
                                    .build()
                    )
                    .build();
            return ResponseEntity.ok(respDTO);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }
}
