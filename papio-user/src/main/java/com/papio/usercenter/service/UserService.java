package com.papio.usercenter.service;

import com.papio.usercenter.domain.dto.msg.UserAddBonusMsgDTO;
import com.papio.usercenter.domain.dto.user.LoginRespDTO;
import com.papio.usercenter.domain.dto.user.UserLoginDTO;
import com.papio.usercenter.domain.entity.user.SysUser;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 14:05 2020/2/6
 * @Modified By:
 **/
public interface UserService {
    /***
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    SysUser findUserById(int id);

    /***
     * 添加Bonus
     * @param userAddBonusMsgDTO
     */
    void addBonus(UserAddBonusMsgDTO userAddBonusMsgDTO);

    /**
     * 登录接口
     * @param loginDTO
     * @param openId
     * @return
     */
    SysUser login(UserLoginDTO loginDTO, String openId);
}
