public class Number {
    private NumberType number_type_;
    private int number_;

    Number(int number, NumberType number_type){
        number_ = number;
        number_type_ = number_type;
    }

    Number(String number) throws Exception {
        try{
            fromRoman(number);
            number_type_ = NumberType.ROMAN;
        } catch (Exception e) {
            number_ = Integer.parseInt(number);
            number_type_ = NumberType.ARABIC;
        }

        if(number_ < 1 || number_ > 10){
            throw new Exception("The number can't be accepted");
        }
    }

    String getNumber(){
        return number_type_ == NumberType.ROMAN ? toRoman() : Integer.toString(number_);
    }

    Number calculate(Number other, String operator) throws Exception {
        if(this.number_type_ != other.number_type_){
            throw new Exception("Different number systems are used simultaneously");
        }

        int number = switch (operator) {
            case "+" -> this.number_ + other.number_;
            case "-" -> this.number_ - other.number_;
            case "*" -> this.number_ * other.number_;
            case "/" -> this.number_ / other.number_;
            default -> throw new Exception("Incorrect mathematical operator");
        };

        if(number_type_ == NumberType.ROMAN && number <= 0){
            throw new Exception("Negative Roman numeral");
        }

        return new Number(number, this.number_type_);
    }

    private void fromRoman(String number) throws IllegalArgumentException {
        number_ = switch (number){
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new IllegalArgumentException();
        };
    }

    private String toRoman(){
        String[] c = {"C","XC","L","XL","X","IX","V","IV","I"};
        int[] n = {100, 90, 50, 40, 10, 9, 5, 4, 1};

        int number = number_;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n.length; i++) {
            while (number - n[i] >= 0) {
                result.append(c[i]);
                number -= n[i];
            }
        }
        return result.toString();
    }
}
