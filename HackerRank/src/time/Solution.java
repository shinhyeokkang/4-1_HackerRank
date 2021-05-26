package time;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'timeConversion' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String timeConversion(String s) {
    // Write your code here
//    	String front = s.substring(0, 8);
//    	String back = s.substring(8);
//    	//System.out.println(front);
//    	//System.out.println(back);
//    	
//    	StringBuffer sb = new StringBuffer();
//    
//    	
//    	String hour = front.substring(0, 2);
//    	String frontrest = front.substring(2);
//    	int nhour = Integer.parseInt(hour);
//    	if(back.equals("AM")) {
//    		if(hour.equals("12")) {
//    			sb.append(front);
//    			sb.replace(0, 2, "00");
//    			String result = sb.toString();
//    			return result;
//    		}
//    		else {
//    			return front;
//    		}
//    		
//    	}else {
//    		if(hour.equals("12")) {
//    			
//    			return front;
//    		}
//    		else {
//    			sb.append(front);
//    			nhour = nhour + 12;
//    			String newhour = Integer.toString(nhour);
//    			sb.replace(0, 2, newhour);
//    			String result = sb.toString();
//    			return result;
//    		}
//    	}
    	String listTime[] = s.split(":");
        String hour = listTime[0];
        String minutes = listTime[1];
        String secounds = listTime[2].substring(0, 2);
        String caser = listTime[2].substring(2, 4);
        if(caser.equals("AM")){
            if(hour.equals("12"))
                   hour="00";

            return hour+":"+minutes+":"+secounds;
        }else{
            if(!hour.equals("12")){
                int h = Integer.parseInt(hour); 
                h = h +12; 
                hour =""+h; 
            }
            return hour+":"+minutes+":"+secounds;
        }
    	
    
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = bufferedReader.readLine();

        String result = Result.timeConversion(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
