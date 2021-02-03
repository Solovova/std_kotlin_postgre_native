package std_kotlin_postgre_native.db.connectors
import java.sql.ResultSet

class ConnectorDB {
    private val connector: ConnectorPostgre = ConnectorPostgre()

    fun sqlQueryRs(query: String): ResultSet {
        val st = connector.getConnection().createStatement()
        return st.executeQuery(query)
    }

    fun sqlQuery(query: String) {
        val st = connector.getConnection().createStatement()
        st.execute(query)
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