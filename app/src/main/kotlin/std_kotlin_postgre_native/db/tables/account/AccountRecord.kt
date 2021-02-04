package std_kotlin_postgre_native.db.tables.account

import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account_entry.AccountEntryTable


class AccountRecord(private val db: ConnectorDB, val id: Int, val name: String) {
    fun createAccountEntryTable() {
        AccountEntryTable(db, this).tableCreate()
    }

    fun dropAccountEntryTable() {
        AccountEntryTable(db, this).tableDrop()
    }
}