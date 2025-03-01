package org.maxgamer.quickshop.Economy;

import java.util.UUID;

import lombok.*;
import org.jetbrains.annotations.*;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.Util.Util;

public class Economy implements EconomyCore {
    @Getter
    @Setter
    private EconomyCore core;
    private QuickShop plugin = QuickShop.instance;

    public Economy(EconomyCore core) {
        this.core = core;
    }

    @Override
    public String toString() {
        return core.getClass().getName().split("_")[1];
    }

    public EconomyType getNowUsing() {
        return EconomyType.fromID(plugin.getConfig().getInt("economy-type"));
    }

    /**
     * Checks that this economy is valid. Returns false if it is not valid.
     *
     * @return True if this economy will work, false if it will not.
     */
    @Override
    public boolean isValid() {
        return core.isValid();
    }

    /**
     * Formats the given number... E.g. 50.5 becomes $50.5 Dollars, or 50
     * Dollars 5 Cents
     *
     * @param balance The given number
     * @return The balance in human readable text.
     */
    @Override
    public String format(double balance) {
        return Util.parseColours(core.format(balance));
        //Fix color issue from some stupid economy plugin....
    }

    @Override
    public boolean deposit(@NotNull UUID name, double amount) {
        return core.deposit(name, amount);
    }

    @Override
    public boolean withdraw(@NotNull UUID name, double amount) {
        return core.withdraw(name, amount);
    }

    @Override
    public boolean transfer(@NotNull UUID from, UUID to, double amount) {
        return core.transfer(from, to, amount);
    }

    @Override
    public double getBalance(@NotNull UUID name) {
        return core.getBalance(name);
    }
}