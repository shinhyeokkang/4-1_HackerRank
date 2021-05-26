package password;
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
     * Complete the 'minimumNumber' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING password
     */


    public static int minimumNumber(int n, String password) {
    // Return the minimum number of characters to make the password strong
    	String numbers = "0123456789";
    	String lower_case = "abcdefghijklmnopqrstuvwxyz";
    	String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	String special_characters = "!@#$%^&*()-+";
    	
    	password.matches("/[0-9]/g/[a-z]/g/[A-Z]/g/[!@#$%^&*()-+]/g");
    	int req = 4;
    	int result = 0;
    	Pattern num = Pattern.compile("[0-9]+");
    	Pattern alpa = Pattern.compile("[a-z]+");
    	Pattern ALPA = Pattern.compile("[A-Z]+");
    	Pattern spec = Pattern.compile("[!@#$%^&*()\\-+]+"); //except for \\-
    	Matcher m1 = num.matcher(password);
    	Matcher m2 = alpa.matcher(password);
    	Matcher m3 = ALPA.matcher(password);
    	Matcher m4 = spec.matcher(password);
    	
      	if(m1.find()) { //따로 해야할듯
    		req = req-1;
    		//System.out.println("0");
    	}  	
      	if(m2.find()) { //따로 해야할듯
    		req = req-1;
    		//System.out.println("a");
    	}
      	if(m3.find()) { //따로 해야할듯
    		req = req-1;
    		//System.out.println("A");
    	}
      	if(m4.find()) { //따로 해야할듯
    		req = req-1;
    		//System.out.println("!");
    	}
      	n = n + req;
      	result = req;
      	//System.out.println(result);
      	if(n<6) {
      		int m = 6 - n;
      		result = req + m;
      	}
      	//System.out.println(result);

    return result;
    }
    

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String password = bufferedReader.readLine();

        int answer = Result.minimumNumber(n, password);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
