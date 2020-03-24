package com.papio.usercenter.service.impl;

import com.papio.usercenter.domain.dto.msg.UserAddBonusMsgDTO;
import com.papio.usercenter.domain.dto.user.UserLoginDTO;
import com.papio.usercenter.domain.entity.user.BonusEventLog;
import com.papio.usercenter.domain.entity.user.SysUser;
import com.papio.usercenter.mapper.user.BonusEventLogMapper;
import com.papio.usercenter.mapper.user.SysUserMapper;
import com.papio.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 14:07 2020/2/6
 * @Modified By:
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final SysUserMapper sysUserMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public SysUser findUserById(int id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBonus(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        Integer userId = userAddBonusMsgDTO.getUserId();
        Integer bonus = userAddBonusMsgDTO.getBonus();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        sysUser.setBonus(sysUser.getBonus()+bonus);
        sysUserMapper.updateByPrimaryKey(sysUser);
        bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userId)
                .value(bonus)
                .event("CONTRIBUTE")
                .description("发表文章加积分")
                .createTime(new Date())
                .build());
    }

    @Override
    public SysUser login(UserLoginDTO loginDTO, String openId) {
        SysUser sysUser = this.sysUserMapper.selectOne(
                SysUser.builder()
                        .wxId(openId)
                        .build()
        );
        if (sysUser == null) {
            sysUser = SysUser.builder()
                    .wxId(openId)
                    .wxNickname(loginDTO.getWxNickName())
                    .avatarUrl(loginDTO.getAvatarUrl())
                    .bonus(300)
                    .roles("user")
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            this.sysUserMapper.insertSelective(sysUser);
        }
        return sysUser;
    }
}
