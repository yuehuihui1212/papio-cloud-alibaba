package com.papio.usercenter.consumer;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 19:09 2020/2/13
 * @Modified By:
 **/
/*@Service
@RocketMQMessageListener(consumerGroup = "consumer",topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {
    private final SysUserMapper sysUserMapper;
    private final BonusEventLogMapper bonusEventLogMapper;
    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDTO) {
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
}*/
