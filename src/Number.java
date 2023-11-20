public class Number {
    NumberType number_type_;
    int number_;

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
        switch (number){
            case "I":
                number_ = 1;
                break;
            case "II":
                number_ = 2;
                break;
            case "III":
                number_ = 3;
                break;
            case "IV":
                number_ = 4;
                break;
            case "V":
                number_ = 5;
                break;
            case "VI":
                number_ = 6;
                break;
            case "VII":
                number_ = 7;
                break;
            case "VIII":
                number_ = 8;
                break;
            case "IX":
                number_ = 9;
                break;
            case "X":
                number_ = 10;
                break;
            default: throw new IllegalArgumentException();
        }
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
