package std_kotlin_postgre_native.db.tables.account_entry

import org.intellij.lang.annotations.Language
import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account.AccountRecord
import std_kotlin_postgre_native.db.tables.document.DocumentRecord
import java.sql.SQLException

class AccountEntryRecord(
    private val db: ConnectorDB,
    val id: Int,
    val accountRecord: AccountRecord,
    val documentRecord: DocumentRecord,
    val sum: Double
) {
    companion object {
        fun recordCreate(db: ConnectorDB, accountRecord: AccountRecord, documentRecord: DocumentRecord,sum: Double): AccountEntryRecord {
            @Language("SQL")
            val queryPush = """
                INSERT INTO account_${accountRecord.id} (document , sum)
                VALUES (${documentRecord.id}, $sum)
                RETURNING id;
            """
            val rs = db.sqlQueryRs(queryPush)
            if (rs.next()) {
                return AccountEntryRecord(db, rs.getInt(1), accountRecord, documentRecord, sum)
            }else{
                throw  SQLException()
            }
        }
    }
}