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

    public static void main(String args[]){
        try {
            int value = (args.length > 0) ? Integer.parseInt(args[0]) : 2904;
            IntToRomanConverter converter = new IntToRomanConverter();
            System.out.println(converter.convert(value));
        } catch (Exception e){
            System.out.println("Expected a valid integer as an input parameter");
        }
    }

    private int resolveRomanValues(int decimalValue, RomanValue rv, StringBuffer result){
        int prefixSubtract = rv.getPrefixSubtractValue() != null? rv.getPrefixSubtractValue().getDecimal() : 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(decimalValue);
        while (stack.peek() >= rv.getDecimal() - prefixSubtract){
            int dv = stack.pop();
            if (dv >= rv.getDecimal()){
                stack.push(dv - rv.getDecimal());
                result.append(rv.getRoman());
            }
            else{
                stack.push(dv - (rv.getDecimal() - prefixSubtract));
                result.append(rv.getPrefixSubtractValue().getRoman());
                result.append(rv.getRoman());
            }
        }
        return stack.pop();
    }

    private RomanValue romanValues[] ;
    public String convert(int decimal){
        StringBuffer result = new StringBuffer();
        Stack<Integer> stack = new Stack<>();
        stack.push(decimal);
        for (RomanValue v : romanValues ) {
            int d = stack.pop();
            stack.push(resolveRomanValues(d,v,result));
        }
        return result.toString();
    }
}


