package std_kotlin_postgre_native.db.tables.document

import org.intellij.lang.annotations.Language
import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account.AccountRecord
import java.sql.SQLException

class DocumentTable(var db: ConnectorDB) {
    fun tableCreate() {
        @Language("SQL")
        val queryTableCreate = """
                CREATE SEQUENCE IF NOT EXISTS document_id_seq
                    START WITH 1
                    INCREMENT BY 1
                    NO MINVALUE
                    NO MAXVALUE
                    CACHE 1;
            
                CREATE TABLE IF NOT EXISTS document (
                    id bigint DEFAULT nextval('document_id_seq'::regclass) NOT NULL PRIMARY KEY,
                    accountFrom bigint REFERENCES account (id),
                    accountTo bigint REFERENCES account (id),
                    sum numeric
                );
            """

        db.sqlQuery(queryTableCreate)
    }

    fun tableDrop() {
        @Language("SQL")
        val queryTableDrop = """
                    DROP TABLE IF EXISTS document;
                    DROP SEQUENCE IF EXISTS document_id_seq ;
                """

        db.sqlQuery(queryTableDrop)
    }

    fun recordCreate(accountRecordFrom: AccountRecord, accountRecordTo: AccountRecord, sum: Double): DocumentRecord {
        @Language("SQL")
        val queryPush = """
                INSERT INTO document (accountFrom, accountTo, sum)
                VALUES (${accountRecordFrom.id}, ${accountRecordTo.id}, $sum)
                RETURNING id;
            """
        val rs = db.sqlQueryRs(queryPush)
        if (rs.next()) {
            val documentRecord = DocumentRecord(db, rs.getInt(1), accountRecordFrom, accountRecordTo, sum)
            documentRecord.createAccountEntryRecords()
            return documentRecord
        }else{
            throw  SQLException()
        }
    }
}