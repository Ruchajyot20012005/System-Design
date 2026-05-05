package com.systemdesign;

class SymbolProvider {
    public static final char[] SYMBOLS = {
            'X', 'O', '△', '□', '*', '@', '#', '$', '&', '%'
    };

    public static char getSymbol(int index) {
        return SYMBOLS[index];
    }

    public static int maxSymbols() {
        return SYMBOLS.length;
    }
}
