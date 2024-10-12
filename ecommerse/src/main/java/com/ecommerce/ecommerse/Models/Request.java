package com.ecommerce.ecommerse.Models;

public class Request {
    private String id;
    private int discount;
    private String phoneNumber;
    private String address;
    private String email;
    private String categoryName;
    private String productName;
    private int quantity;
    private int productId;
    private Integer cartId;
    private boolean is_featured;

    public boolean getIs_featured() {
        return is_featured;
    }

    public Integer getCartId() {
        return cartId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Request(String id, int discount, String phoneNumber, String address, String email) {
        this.id = id;
        this.discount = discount;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

}
