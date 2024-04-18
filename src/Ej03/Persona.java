// Fig. 25.30: Persona.java
// La clase Persona representa una entrada en una libreta de direcciones.

package Ej03;

public class Persona
{
   private int idDireccion;
   private String primerNombre;
   private String apellidoPaterno;
   private String email;
   private String numeroTelefonico;

   // constructor sin argumentos
   public Persona()
   {
   } // fin del constructor de Persona sin argumentos

   // constructor
   public Persona( int id, String nombre, String apellido, 
      String direccionEmail, String telefono )
   {
      establecerIDDireccion( id );
      establecerPrimerNombre( nombre );
      establecerApellidoPaterno( apellido );
      establecerEmail( direccionEmail );
      establecerNumeroTelefonico( telefono );
   } // fin del constructor de Persona con cinco argumentos

   // establece el objeto idDireccion
   public void establecerIDDireccion( int id )
   {
      idDireccion = id;
   } // fin del m�todo establecerIDDireccion

   // devuelve el valor de idDireccion 
   public int obtenerIDDireccion()
   {
      return idDireccion;
   } // fin del m�todo obtenerIDDireccion
   
   // establece el primerNombre
   public void establecerPrimerNombre( String nombre )
   {
      primerNombre = nombre;
   } // fin del m�todo establecerPrimerNombre

   // devuelve el primer nombre 
   public String obtenerPrimerNombre()
   {
      return primerNombre;
   } // fin del m�todo obtenerPrimerNombre
   
   // establece el apellidoPaterno
   public void establecerApellidoPaterno( String apellido )
   {
      apellidoPaterno = apellido;
   } // fin del m�todo establecerApellidoPaterno

   // devuelve el apellido paterno
   public String obtenerApellidoPaterno()
   {
      return apellidoPaterno;
   } // end method obtenerApellidoPaterno
   
   // establece la direcci�n de email
   public void establecerEmail( String direccionEmail )
   {
      email = direccionEmail;
   } // fin del m�todo establecerEmail

   // devuelve la direcci�n de email
   public String obtenerEmail()
   {
      return email;
   } // fin del m�todo obtenerEmail
   
   // establece el n�mero telef�nico
   public void establecerNumeroTelefonico( String telefono )
   {
      numeroTelefonico = telefono;
   } // fin del m�todo establecerNumeroTelefonico

   // devuelve el n�mero telef�nico
   public String obtenerNumeroTelefonico()
   {
      return numeroTelefonico;
   } // fin del m�todo obtenerNumeroTelefonico
} // fin de la clase Persona


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



 