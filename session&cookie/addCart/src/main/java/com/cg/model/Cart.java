package com.cg.model;

import java.util.HashMap;
import java.util.Map;


public class Cart {

    private Map<Product, Integer> products = new HashMap<>();

    public Cart(){}

    public Cart(Map<Product, Integer> products){
        this.products = products;
    }
    public Map<Product, Integer> getProducts(){
        return products;
    }

    private boolean checkItemInCart(Product product){
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            if(entry.getKey().getId().equals(product.getId())){
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectItemInCart(Product product){
        for (Map.Entry<Product, Integer> entry: products.entrySet()){
            if(entry.getKey().getId().equals(product.getId())){
                return entry;
            }
        }
        return null;
    }
     public void minusProduct(Product product){
         Map.Entry<Product, Integer> itemInCart = selectItemInCart(product);
         Integer newQuantity = itemInCart.getValue() - 1;
         products.replace(itemInCart.getKey(), newQuantity);
     }

    public void addProduct(Product product){
        if(!checkItemInCart(product)){
            products.put(product,1);
        }
        else {
            Map.Entry<Product, Integer> itemInCart = selectItemInCart(product);
            Integer newQuantity = itemInCart.getValue() + 1;
            products.replace(itemInCart.getKey(), newQuantity);
        }
    }

    public Integer countProductQuantity(){
        Integer productQantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            productQantity += entry.getValue();
        }
        return productQantity;
    }

    public Integer countItemQuantity(){
        return products.size();
    }

    public Float countTotalPayment(){
        float payment = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            payment += entry.getKey().getPrice()* (float)entry.getValue();
        }
        return payment;
    }

}