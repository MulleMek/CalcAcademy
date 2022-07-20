public class Operand {
    String sourceValue;
    int value;
    Boolean isRoman;
    int convertToArabicNumber( String s){
        return s.replace("CM", "DCD")
                .replace("M", "DD")
                .replace("CD", "CCCC")
                .replace("D", "CCCCC")
                .replace("XC", "LXL")
                .replace("C", "LL")
                .replace("XL", "XXXX")
                .replace("L", "XXXXX")
                .replace("IX", "VIV")
                .replace("X", "VV")
                .replace("IV", "IIII")
                .replace("V", "IIIII").length();
    }

    String convertToRomanNumber( int n){
        return String.valueOf(new char[n]).replace('\0', 'I')
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C")
                .replace("LXL", "XC")
                .replace("CCCCC", "D")
                .replace("CCCC", "CD")
                .replace("DD", "M")
                .replace("DCD", "CM");
    }

    int asArabic(){
        return this.value;
    }

    String asRoman(){
        return convertToRomanNumber(this.value);
    }

    String asAnswer() throws Exception{
        String answer = (isRoman) ? convertToRomanNumber(this.value) : Integer.toString(value) ;

        return answer;
    }

    Operand checkValue() throws Exception{
        if( this.value < 1 || this.value > 10) throw new Exception("Допустимы цыфры в диапазоне от 1 до 10");
        return this;
    }
    Operand( String str) throws Exception {
        this.sourceValue = str.replace(",",".");
        if(this.sourceValue.matches("[^IVXLCDMZ\\d+\\-*\\/]")) throw new Exception("Получены недопустимые данные!");

        this.isRoman = this.sourceValue.matches("^[IVXLCDMZ]+$");
        if(!this.isRoman && Float.parseFloat(this.sourceValue) % 1.0f != 0) throw new Exception("Допустимы только целые числа");

        this.value = (this.isRoman) ? convertToArabicNumber(this.sourceValue) : Integer.parseInt( this.sourceValue );
    }

    Operand(Integer n) {
        this.value = n;
        this.isRoman = false;
        this.sourceValue = n.toString();
    }
}
