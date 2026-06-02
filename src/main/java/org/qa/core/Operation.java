package org.qa.core;

public enum Operation {
    ADD("+"){
        @Override
        double execute(double a, double b){
            return a+b;
        }
    },
    SUBTRACT("-"){
        @Override
        double execute(double a, double b){
            return a-b;
        }
    },
    MULTIPLY("*"){
        @Override
        double execute(double a, double b){
            return a*b;
        }
    },
    DIVIDE("/"){
        double execute(double a, double b){
            if (b == 0){
                throw new IllegalArgumentException("Деление на ноль");
            }
            else {
                return a / b;
            }
        }
    };
    private final String symbol;

    public String getSymbol(){
        return symbol;
    }

    Operation(String symbol){
        this.symbol = symbol;
    }

    abstract double execute(double a, double b);
}
