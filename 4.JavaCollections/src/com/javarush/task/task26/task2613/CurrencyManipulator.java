package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations == null) denominations = new HashMap<>();
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        if (denominations.isEmpty()) return 0;
        Iterator it = denominations.entrySet().iterator();
        int amount = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            amount += (Integer) pair.getKey() * (Integer) pair.getValue();
        }
        return amount;
    }

    public boolean hasMoney() {
        if (denominations == null || getTotalAmount() == 0)
            return false;
        return true;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> cloneOfdenominations = new HashMap<>(denominations);
        int amount = 0;
        Map<Integer, Integer> result = new HashMap<>();

        while (amount < expectedAmount) {
            int maxNeededBanknoteAvailable = 0;
            Iterator it = denominations.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                int banknote = (Integer) pair.getKey();
                int quantity = (Integer) pair.getValue();
                int possibleAmount = amount + banknote;
                 if(banknote >= maxNeededBanknoteAvailable &&
                        banknote <= expectedAmount &&
                        possibleAmount <= expectedAmount &&
                        quantity > 0){
                    maxNeededBanknoteAvailable = banknote;
                }
            }
            amount += maxNeededBanknoteAvailable;
            if (maxNeededBanknoteAvailable == 0 || amount > expectedAmount) {
                denominations = new HashMap<>(cloneOfdenominations);
                throw new NotEnoughMoneyException();
            }
            else  {
                putOneBanknote(result, maxNeededBanknoteAvailable);
                denominations.put(maxNeededBanknoteAvailable, denominations.get(maxNeededBanknoteAvailable) - 1);
            }

            if (amount == expectedAmount) break;
        }
        if(result.isEmpty()) throw new NotEnoughMoneyException();
        return result;
    }

    private void putOneBanknote(Map<Integer, Integer> result, Integer banknote) {
        if (result.containsKey(banknote)) {
            result.put(banknote, result.get(banknote) + 1);
        } else {
            result.put(banknote, 1);
        }
    }
}
