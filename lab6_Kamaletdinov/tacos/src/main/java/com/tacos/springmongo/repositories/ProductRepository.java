/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tacos.springmongo.repositories;
import com.tacos.springmongo.models.Product;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author bayer
 */
public interface ProductRepository extends CrudRepository<Product, String> {
    @Override
    public void delete(Product product);
}
