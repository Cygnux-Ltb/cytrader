package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户表
 * User Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {

    private int userId;

    private String username;

    private String password;

    private String email;

    private String phone;

    private int subAccountId;

}
