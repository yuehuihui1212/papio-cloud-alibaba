package com.papio.contentcenter.feignclient;

import com.papio.contentcenter.domain.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 16:41 2020/2/6
 * @Modified By:
 **/
@FeignClient(name = "user-service")
public interface UserFeignClient {
    /***
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("user/findUserById/{id}")
    UserDTO findUserById(@PathVariable Integer id);

    @GetMapping("user/query")
    UserDTO query(@SpringQueryMap UserDTO userDTO);
}
