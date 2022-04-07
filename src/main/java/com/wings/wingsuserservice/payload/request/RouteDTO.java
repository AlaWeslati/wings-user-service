package com.wings.wingsuserservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {

    private String depar_gov;
    private String adr_departure;
    private String arri_gov;
    private String adr_arrival;
    private double longitude;
    private double latitude;
    private String description;
}
