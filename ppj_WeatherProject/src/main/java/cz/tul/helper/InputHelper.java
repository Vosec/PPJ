package cz.tul.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHelper {
    private Pattern p;
    private Matcher m;

    //returns True if special char found
    public boolean isValidName(String name){
        p = Pattern.compile("[^A-Za-z ]");
        m = p.matcher(name);
        return m.find();
    }
    //returns true if not a number
    public boolean isValidCityId(Integer id){
        p = Pattern.compile("[^0-9]");
        m = p.matcher(id.toString());
        return m.find();
    }
}
