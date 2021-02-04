package std_kotlin_postgre_native.db.tables.document

import org.intellij.lang.annotations.Language
import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account.AccountRecord
import std_kotlin_postgre_native.db.tables.account_entry.AccountEntryRecord
import java.sql.SQLException
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.sql.PreparedStatement
import java.sql.ResultSet


class DocumentRecord(
    private val db: ConnectorDB,
    val id: Int,
    private val accountRecordFrom: AccountRecord,
    private val accountRecordTo: AccountRecord,
    private val sum: Double
) {
    companion object {
        fun recordCreate(
            db: ConnectorDB,
            timestamp: Timestamp,
            accountRecordFrom: AccountRecord,
            accountRecordTo: AccountRecord,
            sum: Double
        ): DocumentRecord {
            db.connector.getConnection()
                .prepareStatement(
                    """
                        INSERT INTO document (time, accountFrom, accountTo, sum)
                        VALUES (?, ?, ?, ?)
                        RETURNING id;"""
                )
                .use { st ->
                    st.setObject(1, timestamp)
                    st.setObject(2, accountRecordFrom.id)
                    st.setObject(3, accountRecordTo.id)
                    st.setObject(4, sum)

                    st.executeQuery().use { rs ->
                        if (rs.next()) {
                            val documentRecord =
                                DocumentRecord(db, rs.getInt(1), accountRecordFrom, accountRecordTo, sum)
                            documentRecord.createAccountEntryRecords()
                            return documentRecord
                        } else {
                            throw  SQLException()
                        }
                    }
                }
        }
    }

    fun createAccountEntryRecords() {
        AccountEntryRecord.recordCreate(db, accountRecordFrom, this, -sum)
        AccountEntryRecord.recordCreate(db, accountRecordTo, this, sum)
    }
}