/**
 * 
 */
package TrabajoFinaSMC6;

/**
 * Alumno: Palomino Jose Dario 
 * Comision N°6 (Grupo CN6)
 * Curso:  1000Programadores Salteños JAVA 
 * Clase :
 
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PanelUsuario {

	Scanner t=new Scanner(System.in);
	Conexion conn=new Conexion();
	
	int fila;
	int columna;
	private Usuario u;
	public PanelUsuario(Usuario u) throws SQLException{
		
		this.u=u;
		System.out.println();
		System.out.println("     *Menu Usuario*       ");
		System.out.println(" 1 - Seleccionar productos");
		System.out.println(" 2 - Carrito");
		System.out.println(" 3 - Alta de compra");
		
		
		int op=t.nextInt();
		switch(op) {
		
		case 1:
			
			this.seleccionarProductos();
			break;
			
		case 2:
			this.VerCarrito();
			break;
		case 3:
			this.FinalizarCompra();
			break;
		}
	}
	
	public void seleccionarProductos() throws SQLException {		
		
		
		int b = 1;
	do {
		String sql = "SELECT * FROM PRODUCTOS";
		ResultSet r = conn.devolverConsulta(sql);
		
		System.out.println("\t               PRODUCTOS               ");
		System.out.println("\t  idProducto - Nombre - CodigoProducto - Stock - Precio  ");
				
		while (r.next()) {
			System.out.println("\t" + r.getInt("idProducto")+ " - " +r.getString("Nombre")+ " - " 
								+ r.getString("codigoBarras") + " - " + r.getString("Stock")+" - "+ "$" +r.getString("Precio"));
		}
		
		//Conexion conn=new Conexion();
		System.out.println("Esta por agregar un producto");
		System.out.println();
		System.out.println("Ingrese el ID del producto");
		String n=t.next();
		System.out.println("Ingrese el nombre del producto");
		String c=t.next();
		System.out.println("Ingrese el precio");
		String s=t.next();

		ArrayList<String >e=new ArrayList<>();
		e.add(n);
		e.add(c);
		e.add(s);
		
		conn.ProductoCarrito(e);
		System.out.println("Producto agregado con exito!");
		System.out.println();
		System.out.println("Desea agregar otro producto 1-SI 0-NO");
		b=t.nextInt();
		}while (b==1);
		
		Usuario u = null;
		PanelUsuario p = new PanelUsuario(u);
	}
	
		
		
	public void VerCarrito() throws SQLException{
		String sql3 = "select * from carrito";
		ResultSet r = conn.devolverConsulta(sql3);
		
		System.out.println();
		System.out.println("\t                CARRITO             ");
		System.out.println("\t--idProducto - Nombre - Precio--");
		
		
		while (r.next()) {
			System.out.println("\t"+r.getInt("idCarrito")+"\t"+ " - "+r.getString("Nombre")+"\t"+ " - "+"$"+r.getInt("Precio"));
		}
		Usuario u = null;
		PanelUsuario p = new PanelUsuario(u);
	}
	
	public void FinalizarCompra() {
		System.out.println();
		System.out.println("GRACIAS POR SU COMPRA");
	}
}