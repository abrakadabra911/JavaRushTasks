package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;


class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int expectedAmount;

        while (true) {

            String amountSTR = ConsoleHelper.readString();
            try {
                try
                {
                    expectedAmount = Integer.parseInt(amountSTR);
                } catch (NumberFormatException e)
                {
                    ConsoleHelper.writeMessage(res.getString("specify.amount"));
                    continue;
                }
                if(expectedAmount<=0) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
                if (currencyManipulator.isAmountAvailable(expectedAmount)) {
                    Map<Integer, Integer> takenMoney = currencyManipulator.withdrawAmount(expectedAmount);
                    Map<Integer, Integer> sorted = new LinkedHashMap<>();

                    ////sorting of Map
                    takenMoney.entrySet().stream()
                            .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                            .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));

                    Iterator it = sorted.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
                    }
                    break;
                }
                else {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                }
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), expectedAmount, currencyCode));  //%d %s was withdrawn successfully
    }
}
/*before=Withdrawing...
success.format=%d %s was withdrawn successfully
specify.amount=Please specify integer amount for withdrawing.
specify.not.empty.amount=Please specify valid positive integer amount for withdrawing.
not.enough.money=Not enough money on your account, please try again
exact.amount.not.available=Exact amount is not available*/