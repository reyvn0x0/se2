package com.example.demo.Factory;

abstract class AchievementAbstract implements IObtainble {
    int Value;
    String Description;
    String Name;
    boolean Status;
     public AchievementAbstract(int value, boolean status, String description, String name) {
         this.Value = value;
         this.Status = status;
         this.Description = description;
         this.Name = name;
     }
    public int getValue(){return Value;};
    public void setDescription(String description){
        Description = description;
    };
    public void setValue(int value){
        Value = value;
    };
    public String getDescription(){return Description;}
    public void setStatus(boolean status){
        this.Status = status;
    };
    public boolean getStatus(){return Status;}
    public String getName(){return Name;}
    public void setName(String name){Name = name;}
}
