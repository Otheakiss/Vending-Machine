package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SalesReport {
    String TEXT_FILE = "C:\\Users\\Student\\workspace\\Mod1Capstone\\module-1-capstone-team-5\\capstone\\"
            + LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
            + "-" + LocalTime.now().format(DateTimeFormatter.ofPattern("hh-mm"))
            + "-sales.txt";

    public SalesReport() throws FileNotFoundException {
        new FileOutputStream(TEXT_FILE);

    }

    public void reportOnInventory(Inventory inventory) throws IOException {
        File myFile = new File(TEXT_FILE);
        BigDecimal total = BigDecimal.ZERO;

        StringBuffer stringBuffer = new StringBuffer();
        for(Item item : inventory.getStock()) {
            stringBuffer.append(item.getName());
            stringBuffer.append("|");
            stringBuffer.append(item.getSold());
            stringBuffer.append('\n');
            if(item.getSold() < 5) {
                total = total.add(item.getPrice().multiply(BigDecimal.valueOf(Long.valueOf(item.getSold()))));
            }
        }
        stringBuffer.append("\n**TOTAL SALES** " + CurrencyHelper.makeItMoney(total));


        try(FileWriter fileWriter = new FileWriter(myFile, true)) {
            fileWriter.append(stringBuffer);
        }
    }
}
