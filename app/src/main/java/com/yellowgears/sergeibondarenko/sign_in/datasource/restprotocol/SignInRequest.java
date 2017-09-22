package com.yellowgears.sergeibondarenko.sign_in.datasource.restprotocol;

public class SignInRequest {

  public String email = "";
  public String password = "";


  public SignInRequest( String email, String password ) {
    if ( email != null ) {
      this.email = email;
    }
    this.password = password;
  }
}
