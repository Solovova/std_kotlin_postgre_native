package std_kotlin_postgre_native.db.tables.account

import org.intellij.lang.annotations.Language
import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account_entry.AccountEntryTable
import java.sql.SQLException


class AccountRecord(private val db: ConnectorDB, val id: Int, val name: String) {
    fun createAccountEntryTable() {
        AccountEntryTable(db, this).tableCreate()
    }
    companion object {
        fun recordCreate(db: ConnectorDB, name: String): AccountRecord {
            @Language("SQL")
            val queryPush = """
                INSERT INTO account ("name")
                VALUES ('$name')
                RETURNING id;
            """
            db.sqlQueryRs(queryPush).use { rs ->
                if (rs.next()) {
                    val accountRecord = AccountRecord(db, rs.getInt(1), name)
                    accountRecord.createAccountEntryTable()
                    return accountRecord
                }else{
                    throw  SQLException()
                }
            }
        }

        fun recordGetByName(db: ConnectorDB, name: String): AccountRecord {
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
    }

    fun dropAccountEntryTable() {
        AccountEntryTable(db, this).tableDrop()
    }
}