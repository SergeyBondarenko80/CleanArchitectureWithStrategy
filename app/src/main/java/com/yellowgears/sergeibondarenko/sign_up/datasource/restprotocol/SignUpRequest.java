package com.yellowgears.sergeibondarenko.sign_up.datasource.restprotocol;

public class SignUpRequest {

  public String email = "";
  public String password = "";
  public String name = "";
  public String phone = "";


  public SignUpRequest( String email, String password, String name, String phone ) {

    this.email = email;
    this.password = password;
    this.email = email;
    this.name = name;
    this.phone = phone;

  }
}
