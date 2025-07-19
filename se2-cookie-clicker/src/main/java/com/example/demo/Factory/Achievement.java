package com.example.demo.Factory;

 class Achievement extends AchievementAbstract {
    public Achievement(int value, String Description, String Name, boolean status) {
        super(value, status, Description, Name);
    }
     @Override
     public int getCost() {
         return 0;
     }
 }
