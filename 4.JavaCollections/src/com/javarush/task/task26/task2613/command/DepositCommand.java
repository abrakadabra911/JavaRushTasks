package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".deposit", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] denominationAndQuantity = ConsoleHelper.getValidTwoDigits(currencyCode);
        if(denominationAndQuantity.length == 0) ConsoleHelper.writeMessage(res.getString("invalid.data"));
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int amount = Integer.parseInt(denominationAndQuantity[0]) * Integer.parseInt(denominationAndQuantity[1]);
        currencyManipulator.addAmount(Integer.parseInt(denominationAndQuantity[0]), Integer.parseInt(denominationAndQuantity[1]));
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, currencyCode));
    }
}
