package utilities;

public class Utility {

    public static float convertStringToFloat(String value) {

        String stringPrice = value.substring(value. indexOf("$")+1);
           stringPrice.trim();

           float floatPrice = Float.parseFloat(stringPrice);
           return floatPrice;
   }
    
}
