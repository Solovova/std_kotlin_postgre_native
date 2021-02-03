package std_kotlin_postgre_native.db.tables

import std_kotlin_postgre_native.db.connectors.DB

class Tables {
    companion object {
        fun createTables(db: DB) {
            Accounts.tableCreate(db)
        }
    }
}