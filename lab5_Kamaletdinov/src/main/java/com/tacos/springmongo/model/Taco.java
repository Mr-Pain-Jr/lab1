/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tacos.springmongo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tacos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taco {
    @Id
    String id;
    String tacoName;
    String tacoDesc;
    Double tacoPrice;
    String tacoImage;
}
