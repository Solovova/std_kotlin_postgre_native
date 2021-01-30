package std_kotlin_postgre_native.db.connectors

import org.intellij.lang.annotations.Language
import java.sql.ResultSet

class DB {
    private val connector: ConnectorPostgre = ConnectorPostgre()

    fun tableCreate(name: String) {
        if (tableExists(name)) return

        @Language("SQL")
        val query = """
            CREATE TABLE $name (
            PersonID int,
            LastName varchar(255),
            FirstName varchar(255),
            Address varchar(255),
            City varchar(255)
        )
        """
        this.sqlQuery(query)
    }

    private fun tableExists(name: String):Boolean {
        @Language("SQL")

        val query = """
            SELECT *
                FROM INFORMATION_SCHEMA . TABLES
                WHERE TABLE_SCHEMA = 'public'
                AND TABLE_NAME = '$name';
        
        """

        val rs = this.sqlQueryRs(query)
        return rs.next()
    }

    private fun rsPrint(rs: ResultSet) {
        while (rs.next()) {
            println("Row: ${rs.row}  -> ")
            for (i in 1..rs.metaData.columnCount) {
                println("   Col $i --> ${rs.metaData.getColumnName(i)} ${rs.getString(i)}")
            }
            println()
        }
    }

    private fun sqlQueryRs(query: String): ResultSet {
        val st = connector.getConnection().createStatement()
        return st.executeQuery(query)
    }

    private fun sqlQuery(query: String) {
        val st = connector.getConnection().createStatement()
        st.execute(query)
    }
}