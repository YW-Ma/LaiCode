package com.study.practice.class_19.InnerClass;

import com.study.practice.class_19.InnerClass.Example2.People;
import com.study.practice.class_19.InnerClass.Example2.SharedBikeUser;

public class Solution {
    public static void main(String[] args) {
        // Instance zhao owns his bike
        People zhao = new People("Zhao");
        People.Bike zhaoBike = zhao.new Bike();  // 注意这个声明
        zhaoBike.printName();

        // People.Bike nobodyBike = new People.Bike(); // 编译错误，People is not an enclosing class.

        // The User class owns shared bikes
        SharedBikeUser.Bike sharedBike1 = new SharedBikeUser.Bike();
        sharedBike1.lock();
        SharedBikeUser.Bike sharedBike2 = new SharedBikeUser.Bike();
        SharedBikeUser.Bike sharedBike3 = new SharedBikeUser.Bike();
        SharedBikeUser.Bike sharedBike4 = new SharedBikeUser.Bike();
        SharedBikeUser.Bike sharedBike5 = new SharedBikeUser.Bike();
        SharedBikeUser anna = new SharedBikeUser("Anna");

        // 注意，People 和 SharedBikeUser的成员都是Bike的Class而不是Bike的instance
        // 所以无法进行 zhao.bike 或者 anna.bike
        // 只能 zhao.Bike, People.Bike, SharedBikeUser.Bike
    }
}