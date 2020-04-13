/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiREST;

/**
 *
 * @author Marcel
 */
public class Comms {
  /* In the ApplicationConfig file inside the entity.service package
   * there is this annotation :
   *
   * @javax.ws.rs.ApplicationPath("webresources")
   * 
    */  
  static String SERVER_REST = 
          "http://localhost:32185/upperCaseRESTJSWebSocket/webresources";
      
}
