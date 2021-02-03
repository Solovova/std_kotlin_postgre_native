package std_kotlin_postgre_native.db.tables

import std_kotlin_postgre_native.db.connectors.ConnectorDB

class Tables {
    companion object {
        fun createTables(db: ConnectorDB) {
            AccountsTable.tableCreate(db)
        }
    }
}