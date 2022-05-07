package com.zhouhui.esms.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("esms_dict")
@Data
public class Dict {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String value;
    private String type;
}
