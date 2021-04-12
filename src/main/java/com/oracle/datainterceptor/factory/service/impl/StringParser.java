package com.oracle.datainterceptor.factory.service.impl;

import com.oracle.datainterceptor.factory.service.ParseType;

/**
 * Service implementation class for String type
 * @author velisela
 *
 */
public class StringParser implements ParseType {

    public String getInput() {
        //This method should read the data from somewhere returns the string
        return "2343225,2345,us_east,RedTeam,ProjectApple,3445s\r\n" + 
        "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\r\n" + 
        "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n" + 
        "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\r\n" + 
        "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
    }

}
