package com.example.account_book;

public class Data {
    private int amount;
    private String category;
    private String content;
    private String fixed_data;

    public Data(){}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFixed_data() {
        return fixed_data;
    }

    public void setFixed_data(String fixed_data) {
        this.fixed_data = fixed_data;
    }
}
