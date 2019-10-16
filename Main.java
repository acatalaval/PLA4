package JDBC_Pla4;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class Main {
     
	public static void main(String[] args) {
		String cadConexion = "jdbc:mysql://localhost:3306/";
		String bd = "ej_ddl_pla4";
		String usuario = "root";
		String pass = "S1p0d3m!";
		try {
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = DriverManager.getConnection(cadConexion + bd, usuario, pass);
		     Statement stmt;
		     PreparedStatement pstmt;
		     ResultSet rs;
		     Scanner entrada = new Scanner(System.in);
		     int res, id, idproveedor;
		     String nif,nombre,direccion;
		     BigDecimal precio_unit;
		     
		     do {
		    	 System.out.println("\nEscoja opción:");
			     System.out.println("1.- PROVEEDORES");
			     System.out.println("2.- PRODUCTOS");
			     System.out.println("0.- Salir");
		    	 
			     res = Integer.parseInt(entrada.nextLine());
			     switch (res) {
			         case 1:      	
			        	   System.out.println("\nEscoja opción:");
					       System.out.println("1.- Ver todos los proveedores");
					       System.out.println("2.- Ver un proveedor concreto");
					       System.out.println("3.- Crear un nuevo proveedor");
					       System.out.println("4.- Modificar un proveedor");
					       System.out.println("5.- Eliminar un proveedor");
					      
					       res = Integer.parseInt(entrada.nextLine());
					       switch (res) {
					           case 1:
					              stmt = con.createStatement();
					              rs = stmt.executeQuery("select * from proveedor");
					              while (rs.next())
					                  System.out.println(rs.getInt("idproveedor") + " " +rs.getString("nif")+ " " +rs.getString("nombre")+ " " +rs.getString("direccion"));
					              break;
					           case 2:
					              System.out.println("Introduzca el id del proveedor que quiere ver: ");
					              id = Integer.parseInt(entrada.nextLine());
					              pstmt = con.prepareStatement("select * from proveedor where idproveedor=?");
					              pstmt.setInt(1, id);
					              rs = pstmt.executeQuery();
					              if (rs.next()) {
					                  System.out.println("ID:" + rs.getString("idproveedor") + " | NIF: " + rs.getString("nif")+ " | Nombre: " + rs.getString("nombre")+ " | Dirección: " + rs.getString("direccion"));
					              } else {
					                  System.out.println("No encontrado");
					                  }
					              break;
					           case 3:
					              System.out.println("Introduzca el nif del nuevo proveedor : ");
					              nif = entrada.nextLine();
					              System.out.println("Introduzca el nombre del nuevo proveedor : ");
					              nombre = entrada.nextLine();
					              System.out.println("Introduzca la direccion del nuevo proveedor : ");
					              direccion = entrada.nextLine();
					              pstmt = con.prepareStatement("insert into proveedor (nif,nombre,direccion) values(?,?,?)");
					              pstmt.setString(1, nif);
					              pstmt.setString(2, nombre);
					              pstmt.setString(3, direccion);
					              pstmt.execute();
					              System.out.println("Nuevo proveedor añadido");
					              break;      
					           case 4:
					              System.out.println("Introduzca el id del proveedor que quiere modificar: ");
					              id = Integer.parseInt(entrada.nextLine());
					              pstmt = con.prepareStatement("select * from proveedor where idproveedor=?");
					              pstmt.setInt(1, id);
					              
					              System.out.println("¿Qué quieres actualizar?");
					              System.out.println("1.- NIF");
							      System.out.println("2.- Nombre Proveedor");
							      System.out.println("3.- Dirección");
							      res = Integer.parseInt(entrada.nextLine());
							      switch (res) {
							           case 1:
					            	       System.out.println("Introduzca el nuevo NIF del proveedor: ");
						                   nif = entrada.nextLine();
						                   pstmt = con.prepareStatement("update proveedor set nif=? where idproveedor=?");
					            	       pstmt.setString(1, nif);
					            	       break;
							           case 2:
							        	   System.out.println("Introduzca el nuevo nombre del proveedor: ");
								           nombre = entrada.nextLine();
								           pstmt = con.prepareStatement("update proveedor set nombre=? where idproveedor=?");
							               pstmt.setString(1, nombre);
							               break;
							           case 3:
							        	   System.out.println("Introduzca la nueva dirección: ");
								           direccion = entrada.nextLine();
								           pstmt = con.prepareStatement("update proveedor set direccion=? where idproveedor=?");
							               pstmt.setString(1, direccion);
							               break;
					              }     
					              pstmt.setInt(2, id);
					              pstmt.execute();
					              System.out.println("Proveedor modificado");
					              break;
					           case 5:
					              System.out.println("Introduzca el id del proveedor que quiere eliminar: ");
					              id = Integer.parseInt(entrada.nextLine());
					              pstmt = con.prepareStatement("delete from proveedor where idproveedor=?");
					              pstmt.setInt(1, id);
					              pstmt.execute();
					              System.out.println("Proveedor eliminado");
					           break;
					           }
			        	 
			        	 
			         break;	 
			         
		    	 
			         case 2:
		    	 
			        	 System.out.println("\nEscoja opción:");
					       System.out.println("1.- Ver todos los productos");
					       System.out.println("2.- Ver un producto concreto");
					       System.out.println("3.- Crear un nuevo producto");
					       System.out.println("4.- Modificar un producto");
					       System.out.println("5.- Eliminar un producto");
					      
					       res = Integer.parseInt(entrada.nextLine());
					       switch (res) {
					           case 1:
					              stmt = con.createStatement();
					              rs = stmt.executeQuery("select * from productos");
					              while (rs.next())
					                  System.out.println(rs.getInt("idproducto") + " " +rs.getString("nombre")+ " " +rs.getBigDecimal("precio_unit")+ " " +rs.getInt("idproveedor"));
					              break;
					           case 2:
					              System.out.println("Introduzca el id del producto que quiere ver: ");
					              id = Integer.parseInt(entrada.nextLine());
					              pstmt = con.prepareStatement("select * from productos where idproducto=?");
					              pstmt.setInt(1, id);
					              rs = pstmt.executeQuery();
					              if (rs.next()) {
					                  System.out.println("ID:" + rs.getInt("idproducto") + " | Nombre: " + rs.getString("nombre")+ " | Precio unidad: " + rs.getBigDecimal("precio_unit")+ " | Id proveedor: " + rs.getInt("idproveedor"));
					              } else {
					                  System.out.println("No encontrado");
					                  }
					              break;
					           case 3:
					              System.out.println("Introduzca el nombre del nuevo producto: ");
					              nombre = entrada.nextLine();
					              System.out.println("Introduzca el precio unitario del nuevo producto: ");
					              precio_unit = entrada.nextBigDecimal();
					              System.out.println("Introduzca el ID del proveedor del producto: ");
					              idproveedor = entrada.nextInt();
					              pstmt = con.prepareStatement("insert into productos (nombre,precio_unit,idproveedor) values(?,?,?)");
					              pstmt.setString(1, nombre);
					              pstmt.setBigDecimal(2, precio_unit);
					              pstmt.setInt(3, idproveedor);
					              pstmt.execute();
					              System.out.println("Nuevo producto añadido");
					              break;      
					           case 4:
					              System.out.println("Introduzca el id del producto que quiere modificar: ");
					              id = Integer.parseInt(entrada.nextLine());
					              pstmt = con.prepareStatement("select * from productos where idproducto=?");
					              pstmt.setInt(1, id);
					              
					              System.out.println("¿Qué quieres actualizar?");
					              System.out.println("1.- Nombre del producto");
							      System.out.println("2.- Precio unitario");
							      System.out.println("3.- Id de proveedor");
							      res = Integer.parseInt(entrada.nextLine());
							      switch (res) {
							           case 1:
					            	       System.out.println("Introduzca el nuevo nombre del producto: ");
						                   nombre = entrada.nextLine();
						                   pstmt = con.prepareStatement("update productos set nombre=? where idproducto=?");
					            	       pstmt.setString(1, nombre);
					            	       break;
							           case 2:
							        	   System.out.println("Introduzca el nuevo precio unitario del producto: ");
								           precio_unit = entrada.nextBigDecimal();
								           pstmt = con.prepareStatement("update productos set precio_unit=? where idproducto=?");
							               pstmt.setBigDecimal(1, precio_unit);
							               break;
							           case 3:
							        	   System.out.println("Introduzca el nuevo id del proveedor: ");
								           idproveedor = entrada.nextInt();
								           pstmt = con.prepareStatement("update productos set idproveedor=? where idproducto=?");
							               pstmt.setInt(1, idproveedor);
							               break;
					              }     
					              pstmt.setInt(2, id);
					              pstmt.execute();
					              System.out.println("Producto modificado");
					              break;
					           case 5:
					              System.out.println("Introduzca el id del producto que quiere eliminar: ");
					              id = Integer.parseInt(entrada.nextLine());
					              pstmt = con.prepareStatement("delete from productos where idproducto=?");
					              pstmt.setInt(1, id);
					              pstmt.execute();
					              System.out.println("Producto eliminado");
					           break;
					           } 
			    
		    	     break;
		             }
		       
		       } while (res != 0);
		             con.close();
		             entrada.close();
		       } catch (Exception e) {
		             System.out.println(e);

               }
		}
	
}
		
		


