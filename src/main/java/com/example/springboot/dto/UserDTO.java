package com.example.springboot.dto;

import com.example.springboot.config.EnumValues;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import com.example.springboot.validationGroups.*;

/**
 * @author lqb
 * @date 2021/12/21 9:51
 */
@Data
public class UserDTO {

    @EnumValues(values = {1,2,3,4},groups = AddUserGroup.class)
    private Integer id;

    @NotEmpty(message = "姓名不能为空1",groups = AddUserGroup.class)
    @NotEmpty(message = "姓名不能为空2",groups = UpdateUserGroup.class)
    private String name;

    @Future(message = "更新时间不能是过去的时间1",groups = AddUserGroup.class )
    @Future(message = "更新时间不能是过去的时间2",groups = UpdateUserGroup.class )
    private Date updateTime;
}
