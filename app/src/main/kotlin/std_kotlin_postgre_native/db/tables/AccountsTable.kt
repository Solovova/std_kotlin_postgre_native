package std_kotlin_postgre_native.db.tables

import org.intellij.lang.annotations.Language
import std_kotlin_postgre_native.db.connectors.ConnectorDB
import java.sql.SQLException

class AccountsTable(var db: ConnectorDB, private val id: Int, private val name: String)  {
    companion object {
        @Language("SQL")
        var queryTableCreate = """
                CREATE SEQUENCE IF NOT EXISTS accounts_id_seq
                    START WITH 1
                    INCREMENT BY 1
                    NO MINVALUE
                    NO MAXVALUE
                    CACHE 1;
            
                CREATE TABLE IF NOT EXISTS accounts (
                    id bigint DEFAULT nextval('accounts_id_seq'::regclass) NOT NULL PRIMARY KEY,
                    "name" varchar(255) NOT NULL UNIQUE,
                    lastModified date
                );
            """

        @Language("SQL")
        var queryTableDrop = """
                    DROP TABLE IF EXISTS accounts;
                    DROP SEQUENCE IF EXISTS accounts_id_seq ;
                """

        fun tableCreate(db: ConnectorDB) {
            db.sqlQuery(this.queryTableCreate)
        }

        fun tableDrop(db: ConnectorDB) {
            db.sqlQuery(this.queryTableDrop)
        }

        fun recordCreate(db: ConnectorDB, name: String): AccountsTable {
            @Language("SQL")
            val queryPush = """
                INSERT INTO accounts ("name")
                VALUES ('$name');
            """
            db.sqlQuery(queryPush)
            return AccountsTable.recordGet(db, name);
        }

        fun recordGet(db: ConnectorDB, name: String): AccountsTable {
            @Language("SQL")
            val queryPresent = """
                SELECT ID
                FROM accounts
                WHERE "name" = '${name}';
            """

            db.sqlQueryRs(queryPresent).use {rs ->
                if (rs.next()) {
                    return AccountsTable(db, rs.getInt(1), name)
                }
                throw SQLException()
            }
        }
    }

}