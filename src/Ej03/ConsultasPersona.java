// Fig. 25.31: ConsultasPersona.java
// Instrucciones PreparedStatement utilizadas por la aplicaci�n Libreta de direcciones

package Ej03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ConsultasPersona 
{
   // nombre del controlador de JDBC y URL de la base de datos 
   static final String CONTROLADOR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";             
   static final String URL_BASEDATOS = "jdbc:sqlserver://localhost;databaseName=LibretaDirecciones;trustServerCertificate=true;";
   private static final String NOMBREUSUARIO = "jhtp7";
   private static final String CONTRASENIA = "jhtp7";

   private Connection conexion = null; // maneja la conexi�n
   private PreparedStatement seleccionarTodasLasPersonas = null; 
   private PreparedStatement seleccionarPersonasPorApellido = null; 
   private PreparedStatement insertarNuevaPersona = null; 
    
   // constructor
   public ConsultasPersona()
   {
      try 
      {
         // carga la clase controlador
         Class.forName( CONTROLADOR );

         conexion = 
            DriverManager.getConnection( URL_BASEDATOS, NOMBREUSUARIO, CONTRASENIA );

         // crea una consulta que selecciona todas las entradas en la LibretaDirecciones
         seleccionarTodasLasPersonas = 
            conexion.prepareStatement( "SELECT * FROM Direcciones" );
         
         // crea una consulta que selecciona las entradas con un apellido espec�fico
         seleccionarPersonasPorApellido = conexion.prepareStatement( 
            "SELECT * FROM Direcciones WHERE ApellidoPaterno = ?" );
         
         // crea instrucci�n insert para agregar una nueva entrada en la base de datos
         insertarNuevaPersona = conexion.prepareStatement( 
            "INSERT INTO Direcciones " + 
            "( PrimerNombre, ApellidoPaterno, Email, NumeroTelefonico ) " + 
            "VALUES ( ?, ?, ?, ? )" );
      } // fin de try
      catch ( SQLException excepcionSql )
      {
         excepcionSql.printStackTrace();
         System.exit( 1 );
      } // fin de catch
      catch ( ClassNotFoundException noEncontroClase )                     
      {                                                                  
         noEncontroClase.printStackTrace();            
	    System.exit( 1 );
      } // fin de catch                                                     

   } // fin del constructor de ConsultasPersona
   
   // selecciona todas las direcciones en la base de datos
   public List< Persona > obtenerTodasLasPersonas()
   {
      List< Persona > resultados = null;
      ResultSet conjuntoResultados = null;
      
      try 
      {
         // executeQuery devuelve ResultSet que contiene las entradas que coinciden
         conjuntoResultados = seleccionarTodasLasPersonas.executeQuery(); 
         resultados = new ArrayList< Persona >();
         
         while ( conjuntoResultados.next() )
         {
            resultados.add( new Persona(
               conjuntoResultados.getInt( "idDireccion" ),
               conjuntoResultados.getString( "primerNombre" ),
               conjuntoResultados.getString( "apellidoPaterno" ),
               conjuntoResultados.getString( "email" ),
               conjuntoResultados.getString( "numeroTelefonico" ) ) );
         } // fin de while
      } // fin de try
      catch ( SQLException excepcionSql )
      {
         excepcionSql.printStackTrace();         
      } // fin de catch
      finally
      {
         try 
         {
            conjuntoResultados.close();
         } // fin de try
         catch ( SQLException excepcionSql )
         {
            excepcionSql.printStackTrace();         
            close();
         } // fin de catch
      } // fin de finally
      
      return resultados;
   } // fin del m�todo obtenerTodasLasPersonaas
   
   // selecciona persona por apellido paterno
   
   public List< Persona > obtenerPersonasPorApellido( String nombre )
   {
      List< Persona > resultados = null;
      ResultSet conjuntoResultados = null;

      try 
      {
         seleccionarPersonasPorApellido.setString( 1, nombre ); // especifica el apellido paterno

         // executeQuery devuelve ResultSet que contiene las entradas que coinciden
         conjuntoResultados = seleccionarPersonasPorApellido.executeQuery(); 

         resultados = new ArrayList< Persona >();

         while ( conjuntoResultados.next() )
         {
            resultados.add( new Persona(
               conjuntoResultados.getInt( "idDireccion" ),
               conjuntoResultados.getString( "primerNombre" ),
               conjuntoResultados.getString( "apellidoPaterno" ),
               conjuntoResultados.getString( "email" ),
               conjuntoResultados.getString( "numeroTelefonico" ) ) );
         } // fin de while
      } // fin de try
      catch ( SQLException excepcionSql )
      {
         excepcionSql.printStackTrace();
      } // fin de catch
      finally
      {
         try 
         {
            conjuntoResultados.close();
         } // fin de try
         catch ( SQLException excepcionSql )
         {
            excepcionSql.printStackTrace();         
            close();
         } // fin de catch
      } // fin de finally
      
      return resultados;
   } // fin del m�todo obtenerPersonasPorApellido
   
   // agrega una entrada
   public int agregarPersona( 
      String pnombre, String apaterno, String email, String num )
   {
      int resultado = 0;
      
      // establece los par�metros, despu�s ejecuta insertarNuevaPersona
      try 
      {
         insertarNuevaPersona.setString( 1, pnombre );
         insertarNuevaPersona.setString( 2, apaterno );
         insertarNuevaPersona.setString( 3, email );
         insertarNuevaPersona.setString( 4, num );

         // inserta la nueva entrada; devuelve # de filas actualizadas
         resultado = insertarNuevaPersona.executeUpdate(); 
      } // fin de try
      catch ( SQLException excepcionSql )
      {
         excepcionSql.printStackTrace();
         close();
      } // fin de catch
      
      return resultado;
   } // fin del m�todo agregarPersona
   
   // cierra la conexi�n a la base de datos
   public void close()
   {
      try 
      {
         conexion.close();
      } // fin de try
      catch ( SQLException excepcionSql )
      {
         excepcionSql.printStackTrace();
      } // fin de catch
   } // fin del m�todo close
} // fin de la interfaz ConsultasPersona


/**************************************************************************
 * (C) Copyright 1992-2007 por Deitel & Associates, Inc. y                *
 * Pearson Education, Inc. Todos los derechos reservados.                 *
 *                                                                        *
 * RENUNCIA: Los autores y el editor de este libro han realizado su mejor *
 * esfuerzo para preparar este libro. Esto incluye el desarrollo, la      *
 * investigaci�n y prueba de las teor�as y programas para determinar su   *
 * efectividad. Los autores y el editor no hacen ninguna garant�a de      *
 * ning�n tipo, expresa o impl�cita, en relaci�n con estos programas o    *
 * con la documentaci�n contenida en estos libros. Los autores y el       *
 * editor no ser�n responsables en ning�n caso por los da�os consecuentes *
 * en conexi�n con, o que surjan de, el suministro, desempe�o o uso de    *
 * estos programas.                                                       *
 *************************************************************************/

 