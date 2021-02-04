package std_kotlin_postgre_native.db.tables.document

import org.intellij.lang.annotations.Language
import std_kotlin_postgre_native.db.connectors.ConnectorDB

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
                    time timestamp,
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


}