import java.sql.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
@Path("/display/")
public class htmltable {
public static Connection getConnection() throws SQLException {
		
		Connection con=null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cognizant?autoReconnect=true","root","password");
		System.out.println("Success");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

public static String getDetails() throws SQLException {
	PreparedStatement preparedStatement3=getConnection().prepareStatement("SELECT max(CPU_Value),avg(CPU_Value) FROM Cpu");
	ResultSet result2=preparedStatement3.executeQuery();
	String max="";
	String avg="";
	while(result2.next()) {
		max=result2.getString(1);
		avg=result2.getString(2);
	}
	String s="<table style=\"border: 1px solid black\"><tr><th style=\"border: 1px solid black\">Transaction Name</th><th style=\"border: 1px solid black\">Max</th><th style=\"border: 1px solid black\">Avg</th></tr><tr><td style=\"border: 1px solid black\">Trasaction 1</td><td style=\"border: 1px solid black\">"+max+"</td><td style=\"border: 1px solid black\">"+avg+"</td></tr></table>";
	return s;
}
@Path("/table")
@GET
@Produces(MediaType.TEXT_HTML)
public String display() throws SQLException {
	String s=getDetails();
	return s;
	
	
}
}