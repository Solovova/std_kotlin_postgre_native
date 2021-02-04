package std_kotlin_postgre_native.db.tables.account_entry

import org.intellij.lang.annotations.Language
import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account.AccountRecord

class AccountEntryTable(private val db: ConnectorDB, private val accountRecord: AccountRecord) {
    fun tableCreate() {
        @Language("SQL")
        val queryTableCreate = """
                CREATE SEQUENCE IF NOT EXISTS account_${accountRecord.id}_id_seq
                    START WITH 1
                    INCREMENT BY 1
                    NO MINVALUE
                    NO MAXVALUE
                    CACHE 1;
            
                CREATE TABLE IF NOT EXISTS account_${accountRecord.id} (
                    id bigint DEFAULT nextval('account_${accountRecord.id}_id_seq'::regclass) NOT NULL PRIMARY KEY,
                    "name" varchar(255) NOT NULL UNIQUE,
                    lastModified date
                );
            """

        db.sqlQuery(queryTableCreate)
    }

    fun tableDrop() {
        @Language("SQL")
        val queryTableDrop = """
                    DROP TABLE IF EXISTS account_${accountRecord.id};
                    DROP SEQUENCE IF EXISTS account_${accountRecord.id}_id_seq;
                """

        db.sqlQuery(queryTableDrop)
    }

}