package std_kotlin_postgre_native

import std_kotlin_postgre_native.db.connectors.DB
import java.sql.SQLException

fun main() {
    try {
        val db = DB()
        db.tableCreate("tetstbl")
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
