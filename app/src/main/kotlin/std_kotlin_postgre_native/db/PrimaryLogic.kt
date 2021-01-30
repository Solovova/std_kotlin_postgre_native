package std_kotlin_postgre_native.db

import java.sql.Connection

class PrimaryLogic {
    fun tableIs(connection: Connection, tableName: String):Boolean {
        return true
    }

    fun tableCreate(connection: Connection, tableName: String) {
    }
}