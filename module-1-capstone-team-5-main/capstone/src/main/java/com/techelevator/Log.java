package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class Log {
    String TEXT_FILE = "C:\\Users\\Student\\workspace\\Mod1Capstone\\module-1-capstone-team-5\\capstone\\Log.txt";

    public Log() throws FileNotFoundException {
        new FileOutputStream(TEXT_FILE);
    }

    public void logMoneyAdded(BigDecimal value, BigDecimal money) throws IOException {
        File myFile = new File(TEXT_FILE);

        try(FileWriter fileWriter = new FileWriter(myFile, true)) {
            fileWriter.append(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                    + " "
                    + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"))
                    +  " FEED MONEY: "
                    + CurrencyHelper.makeItMoney(value)
                    + " "
                    + CurrencyHelper.makeItMoney(money)
                    + '\n');
        }
    }

    public void logGiveChange(BigDecimal value, BigDecimal money) throws IOException {
        File myFile = new File(TEXT_FILE);

        try(FileWriter fileWriter = new FileWriter(myFile, true)) {
            fileWriter.append(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                    + " "
                    + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"))
                    +  " GIVE CHANGE: "
                    + CurrencyHelper.makeItMoney(value)
                    + " "
                    + CurrencyHelper.makeItMoney(money)
                    + '\n');
        }
    }


    public void logItemBought(Item name, BigDecimal money) throws IOException {
        File myFile = new File(TEXT_FILE);

        try(FileWriter fileWriter = new FileWriter(myFile, true)) {
            fileWriter.append(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                    + " "
                    + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"))
                    + " "
                    + name.getItemNameAndLocationAndPrice()
                    + " "
                    + CurrencyHelper.makeItMoney(money)
                    + '\n');
        }
    }
}
