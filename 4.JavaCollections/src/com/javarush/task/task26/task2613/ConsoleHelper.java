package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common", Locale.ENGLISH);

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String read = bis.readLine();
            if (read.toUpperCase().equals("EXIT")) {

                throw new InterruptOperationException();
            }
            return read;
        } catch (IOException e) {
        }
        return null;
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String code = readString();
        if (code.length() == 3) return code.toUpperCase();
        else {
            writeMessage(res.getString("invalid.data"));
            return askCurrencyCode();
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {

        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String userInput = readString();
            String[] strmas = userInput.split(" ");
            try {
                if (strmas.length == 2 && Integer.parseInt(strmas[0]) >= 0 && Integer.parseInt(strmas[1]) >= 0) {
                    return strmas;
                } else {
                    writeMessage(res.getString("invalid.data"));
                }
            } catch (NumberFormatException ex) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        try {
            writeMessage(res.getString("choose.operation"));
            writeMessage(res.getString("operation.INFO") + " - 1");
            writeMessage(res.getString("operation.DEPOSIT") + " - 2");
            writeMessage(res.getString("operation.WITHDRAW") + " - 3");
            writeMessage(res.getString("operation.EXIT") + " - 4");
            int operation = Integer.parseInt(readString());
            return Operation.getAllowableOperationByOrdinal(operation);
        } catch (IllegalArgumentException e) {
            writeMessage(res.getString("invalid.data"));
            return askOperation();
        }
    }
}
