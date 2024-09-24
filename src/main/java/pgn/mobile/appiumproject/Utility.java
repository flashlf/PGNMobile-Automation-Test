/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pgn.mobile.appiumproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author REDHAT
 */
public class Utility {
    
    public static String getLatestOTP() throws IOException, InterruptedException {

        ProcessBuilder pb = new ProcessBuilder("adb", "shell", "content", "query", "--uri", "content://sms/inbox", "--projection", "body", "--sort", "date");
        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line; String otpregex = null;
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        Collections.reverse(lines);
        process.waitFor();
        String sms = lines.get(0);

        // Ekstrak OTP dari SMS
        String regex = "(?=.*PGN).*?(\\b\\d{6}\\b)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sms);

        if (matcher.find()) {
            otpregex = matcher.group(1);
            System.out.println("Extracted OTP: " + otpregex);
        } else {
            System.out.println("No OTP found From PGN, last message received : "+sms);            
        }
        return otpregex;
    }
}
