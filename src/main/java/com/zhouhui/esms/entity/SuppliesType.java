package com.zhouhui.esms.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 物资类别;此为多级目录，一级目录下可以添加二级子目录，展开可以显示二级子目录列表，往下依次类推实现多级目录显示。
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Data
@TableName("esms_supplies_type")
@ApiModel(value = "SuppliesType对象", description = "物资类别;此为多级目录，一级目录下可以添加二级子目录，展开可以显示二级子目录列表，往下依次类推实现多级目录显示。")
public class SuppliesType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("类别id")
    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;

    @ApiModelProperty("类别名称")
    @TableField("type_name")
    private String typeName;

    @ApiModelProperty("级别;代表几级目录")
    @TableField("level")
    private Integer level;

    @ApiModelProperty("父级ID")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("创建人")
    @TableField("created_by")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty("更新人")
    @TableField("updated_by")
    private Integer updatedBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private Date updatedTime;

    @ApiModelProperty("乐观锁")
    @TableField("version")
    @Version
    private Integer version;

    @ApiModelProperty("逻辑删除;0代表未删除，1代表删除，默认值为0")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;


}