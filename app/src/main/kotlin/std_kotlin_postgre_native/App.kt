package std_kotlin_postgre_native

import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.Tables
import java.sql.SQLException

fun main() {
    try {
        val db = ConnectorDB()

        val tables = Tables(db)
        tables.tablesDrop()

//        val accountTable = AccountTable(db)
//        val timeStart = System.currentTimeMillis()
//        for (i in 0..10) {
//            val acc1 = accountTable.recordCreate("acc$i")
//        }
//        println(System.currentTimeMillis() - timeStart)

//        val acc1 = Accounts.getRecord(db, "acc1")
//        println(acc1.id)
//        val acc2 = Accounts.getRecord(db, "acc2")
//        println(acc2.id)

    }catch (e: ClassNotFoundException){
        println("PostgreSQL JDBC Driver is not found. Include it in your library path ")
        e.printStackTrace()
        return
    }catch (e: SQLException){
        println("Connection Failed")
        e.printStackTrace()
        return
    }


}
