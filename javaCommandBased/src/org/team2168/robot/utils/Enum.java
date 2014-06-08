package org.team2168.robot.utils;

public class Enum {
    protected int _enumValue;
    protected Enum(int enumValue) {
        this._enumValue = enumValue;
    }

    public int Value() {
        return this._enumValue;
    }
}