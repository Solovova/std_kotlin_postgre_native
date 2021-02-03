package std_kotlin_postgre_native.db.connectors
import org.intellij.lang.annotations.Language
import java.sql.ResultSet

class DB {
    private val connector: ConnectorPostgre = ConnectorPostgre()

    fun sqlQueryRs(query: String): ResultSet {
        val st = connector.getConnection().createStatement()
        return st.executeQuery(query)
    }

    fun sqlQuery(query: String) {
        val st = connector.getConnection().createStatement()
        st.execute(query)
    }

    fun tableCreate(name: String, body: String) {
        if (tableExists(name)) return

        @Language("SQL")
        val query = """
            CREATE TABLE $name (
            $body
        )
        """
        this.sqlQuery(query)
    }

    fun tableExists(name: String): Boolean {
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
}