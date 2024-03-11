package com.avanes.vkTask.users.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSite {
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
     public class Address {
         private String street;
         private String suite;
         private String city;
         private String zipcode;
         private Geo geo;

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
         public class Geo {
             private Double lat;
             private Double lng;
         }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class Company {
         private String name;
         private String catchPhrase;
         private String bs;
    }
}
