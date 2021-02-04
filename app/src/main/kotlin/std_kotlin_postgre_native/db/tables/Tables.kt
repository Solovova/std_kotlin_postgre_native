package std_kotlin_postgre_native.db.tables

import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.account.AccountRecord
import std_kotlin_postgre_native.db.tables.account.AccountTable
import std_kotlin_postgre_native.db.tables.document.DocumentRecord
import std_kotlin_postgre_native.db.tables.document.DocumentTable
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.LocalDate




class Tables(var db: ConnectorDB) {
    fun tablesDrop() {


        val accountTable = AccountTable(db)
        val accountRecords = accountTable.recordGetAll()
        for (ar in accountRecords) {
            ar.dropAccountEntryTable()
        }
        val documentTable = DocumentTable(db)
        documentTable.tableDrop()
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
        for (i in 0..10) {
            AccountRecord.recordCreate(db, "acc$i")
        }

        val accountRecord2 = AccountRecord.recordGetByName(db,"acc2")
        val accountRecord3 = AccountRecord.recordGetByName(db,"acc3")
        val accountRecord4 = AccountRecord.recordGetByName(db,"acc4")


        for (i in 1..1000) {
            DocumentRecord.recordCreate(db, Timestamp.valueOf(LocalDateTime.now()), accountRecord2, accountRecord4, 10.0)
        }

        DocumentRecord.recordCreate(db, Timestamp.valueOf(LocalDateTime.now()), accountRecord2, accountRecord4, 10.0)
        DocumentRecord.recordCreate(db, Timestamp.valueOf(LocalDateTime.now()), accountRecord3, accountRecord2, 15.0)


    }
}