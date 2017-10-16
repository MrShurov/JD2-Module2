package lesson3.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class NamingStrategy extends PhysicalNamingStrategy {

    Identifier toPhysicalCatalogName(Identifier var1, JdbcEnvironment var2);

    Identifier toPhysicalSchemaName(Identifier var1, JdbcEnvironment var2);

    Identifier toPhysicalTableName(Identifier var1, JdbcEnvironment var2);

    Identifier toPhysicalSequenceName(Identifier var1, JdbcEnvironment var2);

    Identifier toPhysicalColumnName(Identifier var1, JdbcEnvironment var2);
}
