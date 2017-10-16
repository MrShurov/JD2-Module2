package lesson3.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class NamingStrategy implements PhysicalNamingStrategy {
    @Override
    public Identifier toPhysicalCatalogName(Identifier var1, JdbcEnvironment var2){
        return var1;
    }
    @Override
    public Identifier toPhysicalSchemaName(Identifier var1, JdbcEnvironment var2){
        return var1;
    }
    @Override
    public Identifier toPhysicalTableName(Identifier var1, JdbcEnvironment var2){
        String name = "T_" + var1.getText().toUpperCase();
        return Identifier.toIdentifier(name);
    }
    @Override
    public Identifier toPhysicalSequenceName(Identifier var1, JdbcEnvironment var2){
        return var1;
    }
    @Override
    public Identifier toPhysicalColumnName(Identifier var1, JdbcEnvironment var2){
        String name = "F_" + var1.getText().toUpperCase();
        return Identifier.toIdentifier(name);
    }
}
