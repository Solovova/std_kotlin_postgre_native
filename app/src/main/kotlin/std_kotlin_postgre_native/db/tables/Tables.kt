package std_kotlin_postgre_native.db.tables

import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account.AccountTable

class Tables(var db: ConnectorDB) {
    fun tablesDrop() {
        val accountTable = AccountTable(db)
        val accountRecords = accountTable.recordGetAll()
        for (ar in accountRecords) {
            ar.dropAccountEntryTable()
        }
        accountTable.tableDrop(db)
    }

    fun tablesCreate() {
        val accountTable = AccountTable(db)
        accountTable.tableCreate(db)
    }

    fun tablesReCreate() {
        this.tablesDrop()
        this.tablesCreate()
    }
}