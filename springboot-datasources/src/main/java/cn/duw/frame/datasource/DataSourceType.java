package cn.duw.frame.datasource;

/**
 * 数据源类型
 */
public enum DataSourceType {

    LOCAL("local"),
    TEST("test"),
    PROD("prod");

    private String type;

    DataSourceType(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }}
