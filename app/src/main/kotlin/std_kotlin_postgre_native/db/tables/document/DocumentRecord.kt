package std_kotlin_postgre_native.db.tables.document

import org.intellij.lang.annotations.Language
import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account.AccountRecord
import std_kotlin_postgre_native.db.tables.account_entry.AccountEntryRecord
import java.sql.SQLException

class DocumentRecord(
    private val db: ConnectorDB,
    val id: Int,
    private val accountRecordFrom: AccountRecord,
    private val accountRecordTo: AccountRecord,
    private val sum: Double
) {
    companion object {
        fun recordCreate(db: ConnectorDB, accountRecordFrom: AccountRecord, accountRecordTo: AccountRecord, sum: Double): DocumentRecord {
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

    fun createAccountEntryRecords() {
        AccountEntryRecord.recordCreate(db,accountRecordFrom,this,-sum)
        AccountEntryRecord.recordCreate(db,accountRecordTo,this,sum)
    }
}