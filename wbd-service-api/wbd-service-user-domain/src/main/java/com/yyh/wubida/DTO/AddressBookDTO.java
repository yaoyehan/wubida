package com.yyh.wubida.DTO;

import com.yyh.wubida.entity.AddressBook;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author diesel
 * @since 2020-3-30
 * 地址簿
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddressBookDTO extends AddressBook {

    /**
     * 省
     */
    @ApiModelProperty("省")
    private String province;
    /**
     * 市
     */
    @ApiModelProperty("市")
    private String city;
    /**
     * 区域
     */
    @ApiModelProperty("区")
    private String county;
    /**
     * 全地址
     */
    @ApiModelProperty("全地址")
    private String fullAddress;

}
