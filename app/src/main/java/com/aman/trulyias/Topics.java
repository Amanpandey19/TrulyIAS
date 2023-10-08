package com.aman.trulyias;

import android.graphics.drawable.Drawable;

public class Topics {
    String Name;
    String Desc;
    Drawable Img;

    public Topics(String name, String desc, Drawable img) {
        Name = name;
        Desc = desc;
        Img = img;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public Drawable getImg() {
        return Img;
    }

    public void setImg(Drawable img) {
        Img = img;
    }
}
