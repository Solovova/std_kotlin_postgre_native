package std_kotlin_postgre_native.db.tables

import org.intellij.lang.annotations.Language
import std_kotlin_postgre_native.db.connectors.DB
import java.sql.ResultSet
import java.sql.SQLException

class Accounts(val db: DB, val id: Int, val accName: String) {

    companion object {
        private fun getTableName(): String = "accounts"

        fun tableDrop(db: DB) {
            @Language("SQL")
            val queryDropTable = """
                    DROP TABLE IF EXISTS ${Accounts.getTableName()} 
                """
            db.sqlQuery(queryDropTable)

            @Language("SQL")
            val queryDropSequence = """
                    DROP SEQUENCE IF EXISTS ${Accounts.getTableName()}_id_seq ;
                """
            db.sqlQuery(queryDropSequence)
        }

        fun tableCreate(db: DB) {
            if (db.tableExists(Accounts.getTableName())) return
            @Language("SQL")
            val queryTableCreate: String = """
                CREATE SEQUENCE ${Accounts.getTableName()}_id_seq
                    START WITH 1
                    INCREMENT BY 1
                    NO MINVALUE
                    NO MAXVALUE
                    CACHE 1;
            
                CREATE TABLE ${Accounts.getTableName()} (
                    id bigint DEFAULT nextval('${Accounts.getTableName()}_id_seq'::regclass) NOT NULL PRIMARY KEY,
                    accName varchar(255) NOT NULL UNIQUE,
                    lastModified date
                );
            """
            db.sqlQuery(queryTableCreate)
        }

        fun recordCreate(db: DB, name: String): Accounts {
            @Language("SQL")
            val queryPush = """
                INSERT INTO ${Accounts.getTableName()} (accName)
                VALUES ('$name');
            """
            db.sqlQuery(queryPush)
            return Accounts.recordGet(db, name);
        }

        fun recordGet(db: DB, name: String): Accounts {
            @Language("SQL")
            val queryPresent = """
                SELECT ID
                FROM ${Accounts.getTableName()}
                WHERE accName = '${name}';
            """

            db.sqlQueryRs(queryPresent).use {rs ->
                if (rs.next()) {
                    return Accounts(db, rs.getInt(1), name)
                }
                throw SQLException()
            }
        }
    }
}