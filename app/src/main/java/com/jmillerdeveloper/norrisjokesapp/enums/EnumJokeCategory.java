package com.jmillerdeveloper.norrisjokesapp.enums;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EnumJokeCategory {
    public enum Category{
        animal, career, celebrity, dev, fashion, food, history, money,
        movie, music, political, religion, science, sport, travel
    }

    public enum SpinnerCategory {
        Random,
        Animal, Career, Celebrity, Dev, Fashion, Food, History, Money,
        Movie, Music, Political, Religion, Science, Sport, Travel
    }

    /*INFO:
    * returns a random enum from the enum
    category listed above, used when the user
    doesn't specify what category joke they want.
    *
    * *Necessary to ensure explicit jokes are excluded*
    * */
    public static Category getRandomCategory() {
        Category[] categories = Category.values();
        int randomIndex = new Random().nextInt(categories.length);

        Log.i("classJokeCategory_debug",
                "app: returning random category: "+categories[randomIndex]);
        return categories[randomIndex];
    }

    public static List<String> enumAsList(){
        List<String> enumList = new ArrayList<>();
        SpinnerCategory[] values = SpinnerCategory.values();

        for (SpinnerCategory category : values){
            String categoryName = category.name();
            enumList.add(categoryName);
        }
        return enumList;
    };
}
