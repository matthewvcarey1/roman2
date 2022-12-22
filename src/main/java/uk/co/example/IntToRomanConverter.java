package uk.co.example;

import java.util.Stack;

public class IntToRomanConverter {
    public IntToRomanConverter(){
        romanValues = new RomanValue[7];
        romanValues[6] = new RomanValue(1, 'I', null);
        romanValues[5] = new RomanValue(5, 'V' , romanValues[6]);
        romanValues[4] = new RomanValue(10, 'X' , romanValues[6]);
        romanValues[3] = new RomanValue(50, 'L',  romanValues[4]);
        romanValues[2] = new RomanValue(100, 'C', romanValues[4]);
        romanValues[1] = new RomanValue(500, 'D', romanValues[2]);
        romanValues[0] = new RomanValue(1000, 'M', romanValues[2]);
    }

    private static final String message = "Expected a valid positive integer greater than 0, as an input parameter";
    public static void main(String args[]){
        try {
            final int value = (args.length > 0) ? Integer.parseInt(args[0]) : 0;
            if (value <= 0){
                System.out.println(message);
                return;
            }
            IntToRomanConverter converter = new IntToRomanConverter();
            System.out.println(converter.convert(value));
        } catch (Exception e){
            System.out.println(message);
        }
    }

    private int resolveRomanValues(int decimalValue, RomanValue rv, StringBuffer result){
        final int prefixSubtract = rv.getPrefixSubtractValue() != null? rv.getPrefixSubtractValue().getDecimal() : 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(decimalValue);
        while (stack.peek() >= rv.getDecimal() - prefixSubtract){
            final int dv = stack.pop();
            final int reduction = dv < rv.getDecimal() ? prefixSubtract : 0;
            stack.push(dv - (rv.getDecimal() - reduction));
            if(reduction > 0) result.append(rv.getPrefixSubtractValue().getRoman());
            result.append(rv.getRoman());
        }
        return stack.pop();
    }

    private RomanValue romanValues[] ;
    public String convert(int decimal){
        StringBuffer result = new StringBuffer();
        Stack<Integer> stack = new Stack<>();
        stack.push(decimal);
        for (RomanValue v : romanValues ) {
            final int d = stack.pop();
            stack.push(resolveRomanValues(d,v,result));
        }
        return result.toString();
    }
}


