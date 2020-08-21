package cn.duw.frame.datasource;

/**
 * 数据源类型
 */
public enum TargetDataSource {

    LOCAL("local"),
    RECOMMEND("recommend");

    private String type;

    TargetDataSource(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }}
