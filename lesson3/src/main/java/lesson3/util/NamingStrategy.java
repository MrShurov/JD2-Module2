package lesson3.util;

import org.hibernate.cfg.DefaultNamingStrategy;

public class NamingStrategy extends DefaultNamingStrategy{

    @Override
    public String classToTableName(String className){
        return "T_" + super.classToTableName(className).toUpperCase();
    }

    @Override
    public String propertyToColumnName(String propName){
        return "F_" + super.propertyToColumnName(propName);
    }

    @Override
    public String columnName(String columnName) {
        return columnName(columnName);
    }

    @Override
    public String tableName(String tableName) {
        return tableName;
    }
}
