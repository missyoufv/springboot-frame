package cn.duw.frame.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 分桶配置表
 */
@Data
public class BucketConfig implements Serializable {

    private static final long serialVersionUID = 2227633832612925120L;
    private Integer id;

    /**
     * 尾号
     */
    private String tail;

    /**
     * tab名称对应的channel 比如电影、电视剧、少儿、动漫等等tab
     */
    private String tabChannel;

    /**
     * 算法
     */
    private String algorithm;

    /**
     * 算法版本
     */
    private String algoVersion;

    /**
     * 列名(hbase)
     */
    private String familyName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 分桶类型：0瀑布流、1栏目、2小窗 3个性化栏目 4片尾推荐 5临时业务冲刺
     */
    private String type;

    /**
     * 表名(hbase)
     */
    private String tableName;

    /**
     * 是否使用channel组装rowkey 0不使用 1使用 默认1使用
     */
    private String useChannel;
    /**
     * 状态 1有效 0无效
     */
    private String status;

}
