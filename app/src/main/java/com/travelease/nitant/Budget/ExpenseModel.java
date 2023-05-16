package com.travelease.nitant.Budget;

public class ExpenseModel {

    private Integer Id;
    private Integer Expense;
    private String Uid;
    private String Activity;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getExpense() {
        return Expense;
    }

    public void setExpense(Integer expense) {
        Expense = expense;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }
}
