package std_kotlin_postgre_native.db.tables.document

import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account.AccountRecord
import std_kotlin_postgre_native.db.tables.account_entry.AccountEntryRecord

class DocumentRecord(
    private val db: ConnectorDB,
    val id: Int,
    private val accountRecordFrom: AccountRecord,
    private val accountRecordTo: AccountRecord,
    private val sum: Double
) {
    fun createAccountEntryRecords() {
        AccountEntryRecord.recordCreate(db,accountRecordFrom,this,-sum)
        AccountEntryRecord.recordCreate(db,accountRecordTo,this,sum)
    }
}