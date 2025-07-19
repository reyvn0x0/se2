package com.example.demo.Factory;

abstract class UpgradeAbstract implements IObtainble {
    int Value;
    boolean Status;
    String Description;
    String Name;
    int Cost;
    public UpgradeAbstract(int value, boolean status, String description, String name, int cost){
            this.Value = value;
            this.Status = status;
            this.Description = description;
            this.Name = name;
            this.Cost = cost;
    }
    public int getCost(){return Cost;}
    public int getValue() {
        return Value;
    }
    public void setValue(int value) {
        Value = value;
    }
    public boolean getStatus() {
        return Status;
    }
    public void setStatus(boolean status) {
        this.Status = status;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {Name = name;}
    public void setCost(int cost){Cost = cost;}
}
