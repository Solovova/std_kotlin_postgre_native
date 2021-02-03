package std_kotlin_postgre_native

import std_kotlin_postgre_native.db.connectors.DB
import std_kotlin_postgre_native.db.tables.Accounts
import std_kotlin_postgre_native.db.tables.Tables
import java.sql.SQLException

fun main() {
    try {
        val db = DB()
        Accounts.tableDrop(db)
        Tables.createTables(db)
        val acc1 = Accounts.recordCreate(db, "acc1")
        val acc2 = Accounts.recordCreate(db, "acc2")

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
