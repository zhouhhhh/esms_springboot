package com.zhouhui.esms.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableField;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableName;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;

/**
* <p>
    * 
    * </p>
*
* @author zhouhui
* @since 2022-05-20
*/
    @Data
    @TableName("esms_supplies_mid_type")
    @ApiModel(value = "SuppliesMidType对象", description = "")
    public class SuppliesMidType implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty("物资id")
        @TableField("supplies_id")
    private Integer suppliesId;

            @ApiModelProperty("物资类型id")
        @TableField("supplies_type_id")
    private Integer suppliesTypeId;


}