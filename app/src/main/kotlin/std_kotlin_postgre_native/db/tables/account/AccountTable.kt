package std_kotlin_postgre_native.db.tables.account

import org.intellij.lang.annotations.Language
import std_kotlin_postgre_native.db.connectors.ConnectorDB
import java.sql.SQLException

class AccountTable(var db: ConnectorDB) {
    fun tableCreate() {
        @Language("SQL")
        val queryTableCreate = """
                CREATE SEQUENCE IF NOT EXISTS account_id_seq
                    START WITH 1
                    INCREMENT BY 1
                    NO MINVALUE
                    NO MAXVALUE
                    CACHE 1;
            
                CREATE TABLE IF NOT EXISTS account (
                    id bigint DEFAULT nextval('account_id_seq'::regclass) NOT NULL PRIMARY KEY,
                    "name" varchar(255) NOT NULL UNIQUE,
                    lastModified date
                );
            """

        db.sqlQuery(queryTableCreate)
    }

    fun tableDrop() {
        @Language("SQL")
        val queryTableDrop = """
                    DROP TABLE IF EXISTS account;
                    DROP SEQUENCE IF EXISTS account_id_seq ;
                """

        db.sqlQuery(queryTableDrop)
    }

    fun recordCreate(name: String): AccountRecord {
        @Language("SQL")
        val queryPush = """
                INSERT INTO account ("name")
                VALUES ('$name');
            """
        db.sqlQuery(queryPush)
        val accountRecord = recordGetByName(name)
        accountRecord.createAccountEntryTable()
        return accountRecord
    }

    fun recordGetByName(name: String): AccountRecord {
        @Language("SQL")
        val queryPresent = """
                SELECT ID
                FROM account
                WHERE "name" = '${name}';
            """

        db.sqlQueryRs(queryPresent).use { rs ->
            if (rs.next()) {
                return AccountRecord(db, rs.getInt(1), name)
            }
            throw SQLException()
        }
    }

    fun recordGetAll(): List<AccountRecord> {
        val result:MutableList<AccountRecord> = mutableListOf()

        if (!db.tableExists("account")) {
            return result
        }

        @Language("SQL")
        val queryPresent = """
                SELECT ID, NAME
                FROM account;
            """

        db.sqlQueryRs(queryPresent).use { rs ->
            while (rs.next()) {
                result.add(AccountRecord(db, rs.getInt(1), rs.getString(2)))
            }
        }
        return result
    }
}