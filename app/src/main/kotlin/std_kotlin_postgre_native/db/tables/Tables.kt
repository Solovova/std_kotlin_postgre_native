package std_kotlin_postgre_native.db.tables

import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account.AccountTable
import std_kotlin_postgre_native.db.tables.document.DocumentTable

class Tables(var db: ConnectorDB) {
    fun tablesDrop() {
        val documentTable = DocumentTable(db)
        documentTable.tableDrop()

        val accountTable = AccountTable(db)
        val accountRecords = accountTable.recordGetAll()
        for (ar in accountRecords) {
            ar.dropAccountEntryTable()
        }
        accountTable.tableDrop()
    }

    fun tablesCreate() {
        val accountTable = AccountTable(db)
        accountTable.tableCreate()

        val documentTable = DocumentTable(db)
        documentTable.tableCreate()
    }

    fun tablesReCreate() {
        this.tablesDrop()
        this.tablesCreate()
    }

    fun createTestData() {
        val accountTable = AccountTable(db)
        for (i in 0..10) {
            accountTable.recordCreate("acc$i")
        }

        val accountRecord2 = accountTable.recordGetByName("acc2")
        val accountRecord3 = accountTable.recordGetByName("acc3")
        val accountRecord4 = accountTable.recordGetByName("acc4")

        val documentTable = DocumentTable(db)
        documentTable.recordCreate(accountRecord2, accountRecord4, 10.0)
        documentTable.recordCreate(accountRecord3, accountRecord2, 15.0)

    }
}