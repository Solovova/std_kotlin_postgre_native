package std_kotlin_postgre_native.db

import java.sql.DriverManager
import java.sql.Connection
import java.sql.SQLException

class PostGreConnection {
    companion object {
        private const val DB_URL: String = "jdbc:postgresql://127.0.0.1:5432/solotest"
        private const val USER: String = "postgres"
        private const val PASS: String = "vbwqu1pa"

        fun testConnection() {
            println("Testing connection to PostgreSQL JDBC")

            try {
                Class.forName("org.postgresql.Driver")
            } catch (e: ClassNotFoundException) {
                println("PostgreSQL JDBC Driver is not found. Include it in your library path ")
                e.printStackTrace()
                return
            }

            println("PostgreSQL JDBC Driver successfully connected")
            val connection: Connection?

            try {
                connection = DriverManager
                    .getConnection(DB_URL, USER, PASS)

            } catch (e: SQLException) {
                println("Connection Failed")
                e.printStackTrace()
                return
            }

            if (connection != null) {
                println("You successfully connected to database now")
            } else {
                println("Failed to make connection to database")
            }
        }
    }
}