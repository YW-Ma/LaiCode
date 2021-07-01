package com.study.practice.class_19.generics;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    public void user() {
        List<User> users = new ArrayList<>(); // Generics是尖括号里面的，一般是放尽可能宽泛的parent class
        users.add(new UserCN());
        users.add(new UserUS());
        users.add(new User());
        // 限定了只能加这几种，加入了其他类型的就会在编译时报错

        // 用Generics接，也更放心：不用担心 type conversion error
        User user = users.get(0);

        // 可能有Type conversion error的代码：
        UserCN userCN = (UserCN)users.get(0);
    }
}

class User {}
class UserCN extends User{}
class UserUS extends User{}
