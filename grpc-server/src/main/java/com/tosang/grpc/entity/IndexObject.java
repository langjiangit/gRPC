package com.tosang.grpc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Author: tosang
 * @Date: 2020/8/14 15:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "find_query")
public class IndexObject {
    @Id // 指定ID
    String id = "";

    @Field("证券代码") // 指定域名，覆盖默认
    String securitiesCode = "";

    @Field("ISIN代码")
    String ISIN = "";

    @Field("中文证券名称（短）")
    String nameOfSecuritiesInChinese = "";

    @Field("ST(连续一年亏损)")
    boolean ST = false;

    @Field("*ST(连续两年亏损)")
    boolean starST = false;

    @Field("英文证券名称")
    String nameOfSecuritiesInEnglish = "";

    @Field("基础证券代码")
    String underlyingSecurityCode = "";

    @Field("证券类别")
    String typesOfSecurities = "";

    @Field("最后交易日期")
    String lastTransactionDate = "";

    @Field("上市日期")
    String listingDate = "";

    @Field("除权")
    String exRight = "";

    @Field("除息")
    String exDividend = "";

    @Field("价格档位")
    String priceRange = "";

    @Field("涨跌幅限制类型")
    String limitTypes = "";

    @Field("涨幅上限价格")
    String ceilingPrice = "";

    @Field("跌幅下限价格")
    String lowerLimitPrice = "";
}
