package std_kotlin_postgre_native

import std_kotlin_postgre_native.db.DBLogic
import java.sql.SQLException

fun main() {
    try {
        val db = DBLogic()
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
