package std_kotlin_postgre_native

import std_kotlin_postgre_native.db.connectors.ConnectorDB
import std_kotlin_postgre_native.db.tables.Tables
import std_kotlin_postgre_native.db.tables.account.AccountTable
import java.sql.SQLException

fun main() {
    try {
        val db = ConnectorDB()

        val tables = Tables(db)
        tables.tablesDrop()
        tables.tablesCreate()


        val timeStart = System.currentTimeMillis()

        tables.createTestData()

        println(System.currentTimeMillis() - timeStart)


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
