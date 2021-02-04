https://postgrespro.ru/docs

Example 5.5. Reading Java 8 Date and Time values using JDBC

Statement st = conn.createStatement();
ResultSet rs = st.executeQuery("SELECT * FROM mytable WHERE columnfoo = 500");
while (rs.next())
{
   System.out.print("Column 1 returned ");
   LocalDate localDate = rs.getObject(1, LocalDate.class));
   System.out.println(localDate);
} rs.close();
st.close();

For other data types simply pass other classes to #getObject. Note that the Java data types needs to match the SQL data types in table 7.1.

Example 5.5. Writing Java 8 Date and Time values using JDBC

LocalDate localDate = LocalDate.now();
PreparedStatement st = conn.prepareStatement("INSERT INTO mytable (columnfoo) VALUES (?)");
st.setObject(1, localDate);
st.executeUpdate(); st.close();