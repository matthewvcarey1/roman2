package uk.co.example;

public final class RomanValue {
    public RomanValue(int decimal, char roman, RomanValue prefixSubtractValue ){
        this.decimal = decimal;
        this.roman = roman;
        this.prefixSubtractValue = prefixSubtractValue;
    }
    private final int decimal;
    private final char roman;
    private final RomanValue prefixSubtractValue;

    public int getDecimal(){
        return decimal;
    }

    public char getRoman(){
        return roman;
    }

    public RomanValue getPrefixSubtractValue(){
        return prefixSubtractValue;
    }

}
