package com.wings.wingsuserservice.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {

  private String firstname;
  private String lastname;
  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
  private String governorate;
  private String address;
  private Set<String> role;
  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
  private String phone;
  private String cin;
  private String account_holder;
  private String bank_name ;
  private String agency_name ;
  private String agency_city ;
  private String rib ;


  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

  public String getGovernorate() {
    return governorate;
  }

  public void setGovernorate(String governorate) {
    this.governorate = governorate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCin() {
    return cin;
  }

  public void setCin(String cin) {
    this.cin = cin;
  }

  public String getAccount_holder() {
    return account_holder;
  }

  public void setAccount_holder(String account_holder) {
    this.account_holder = account_holder;
  }

  public String getBank_name() {
    return bank_name;
  }

  public void setBank_name(String bank_name) {
    this.bank_name = bank_name;
  }

  public String getAgency_name() {
    return agency_name;
  }

  public void setAgency_name(String agency_name) {
    this.agency_name = agency_name;
  }

  public String getAgency_city() {
    return agency_city;
  }

  public void setAgency_city(String agency_city) {
    this.agency_city = agency_city;
  }

  public String getRib() {
    return rib;
  }

  public void setRib(String rib) {
    this.rib = rib;
  }



}
