package std_kotlin_postgre_native.db.logic

import java.sql.Connection

class LogicPrimary {
    fun tableIs(connection: Connection, tableName: String):Boolean {
        return true
    }

    fun tableCreate(connection: Connection, tableName: String) {
    }
}