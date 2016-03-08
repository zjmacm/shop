package com.shop.dao;

/**
 * 常用数据库类型
 * Created by ldz on 31/10/14.
 * @author ldz
 */
public final class DBType {
    private final String type;
    private final String version;
    public static final DBType ORACLE = createSingleton("oracle","");
    public static final DBType DB2 = createSingleton("db2","");
    public static final DBType MYSQL = createSingleton("mysql","");
    public static final DBType POSTGRESQL = createSingleton("postgresql","");



    public DBType(String type, String version,Boolean defaultDB) {
        this.type = type.intern();
        this.version = version.intern();
    }

    public DBType(String type, String version) {
        this.type = type.intern();
        this.version = version.intern();
    }

    private DBType(String type, String version, boolean flag) {
        this.type = type;
        this.version = version;
    }

    private static DBType createSingleton(String type, String version) {
        DBType dbtype = new DBType(type, version, false);
        return dbtype;
    }

    public boolean equals(Object obj) {
        if (this == obj) // quick check
            return true;
        if (!(obj instanceof DBType))
            return false;
        DBType other = (DBType) obj;
        return type == other.type && version == other.version;
    }

    public String getVersion() {
        return version;
    }

    public String getType() {
        return type;
    }
}
