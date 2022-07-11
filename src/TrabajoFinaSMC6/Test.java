
package TrabajoFinaSMC6;

/**
 * Alumno: Palomino Jose Dario 
 * Comision N°6 (Grupo CN6)
 * Curso:  1000Programadores Salteños JAVA 
 * Clase :
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class Test {

	
	static Scanner t = new Scanner(System.in);
	static Conexion connn = new Conexion();
	public static void main(String[] args) throws SQLException, InterruptedException, IOException {
		
		
		System.out.println();
		System.out.println();
		
		System.out.println("        BIENVENIDO - SUPERMERCADO -         ");
		
		System.out.println();
		System.out.println("       MENU PRINCIPAL       ");
		System.out.println("        1 - INGRESAR ");
	
		
		
		int opElegida=t.nextInt();

		switch(opElegida) {
		
		case 1:
			System.out.println("   Ingrese correo electronico   ");
			String correo= t.next();
			System.out.println("   Ingrese su clave");
			String pass=t.next();
			
			Ingresar(correo,pass);
			System.out.print(""); 
			break;
		
		default:


			System.out.println("BIENVENIDO REGISTRO");
			System.out.println("INGRESE  NOMBRE ");
			String n=t.next();
			System.out.println("INGRESE  APELLIDO");
			String ap=t.next();
			System.out.println("INGRESE  DNI");
			String d=t.next();
			System.out.println("INGRESE  CORREO");
			String c=t.next();
			System.out.println("INGRESE  CONTRASEÑA");
			String pas=t.next();
			System.out.println("REPITA  CONTRASEÑA");
			String pass2=t.next();
			ArrayList<String > listaadd=new ArrayList<String>();
			
			if(pas.equals(pass2) && n.isEmpty()==false) {
		
				listaadd.add(n);
				listaadd.add(ap);
				listaadd.add(d);
				listaadd.add(c);
				listaadd.add(pas);
				listaadd.add("0");
				listaadd.add("2");
				listaadd.add("1");	
				connn.AgregarUsuario(listaadd);
			
				ResultSet r=connn.devolverConsulta("select idUsuario from Usuario where correo="+"'"+c+"'"+";");
				if(r.next()) {
					
					connn.EjecutarConsulta("insert into Carrito values (null,"+r.getInt("idUsuario")+");");
					
				}
				
			}else {
				System.out.print("Contraseñas incorrecta");
			}
			
			
			break;
		}
		

	}
	
	public static void Ingresar( String c,String pass) throws SQLException {
		
		
		Login login = new Login(c,pass);
		ResultSet r = login.Ingresar();
		if (r.next()) {

			if (r.getInt("idRol") == 1) {
				System.out.println();
				System.out.println("Bienvenido Administrador");
				Usuario u = new Usuario(r.getInt("idUsuario"), r.getString("Nombre"), r.getString("Apellido"),
						r.getString("dni"), r.getString("correo"),r.getString("pass"), r.getInt("EsFrecuente"),
						r.getInt("idRol"), r.getInt("idDomicilio"));
				
				PanelAdmin p = new PanelAdmin(u);
				

			} else {
				System.out.println();
				System.out.print("Bienvenido cliente" + " " + r.getString("Nombre") + " " + r.getString("Apellido"));
				System.out.println();
				Usuario u = new Usuario(r.getInt("idUsuario"), r.getString("Nombre"), r.getString("Apellido"),
						r.getString("dni"), r.getNString("correo"), r.getString("pass"), r.getInt("EsFrecuente"),
						r.getInt("idRol"), r.getInt("idDomicilio"));
				PanelUsuario p = new PanelUsuario(u);
			}
		} else {
			System.out.println("Usuario o contraseña incorrecto");
		}
	}
}