package kma.qlbh.dao;

/**
 * @createAt Dec 1, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class QueryAdapter {

    protected String tableName;
    protected String[] fields;

    public QueryAdapter(String tableName, String[] fields) {
        this.tableName = tableName;
        this.fields = fields;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public String getAllQuery() {
        return "SELECT * FROM " + tableName;
    }
}
