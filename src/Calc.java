public class Calc {
    String enteredStr;
    char sign = 0;
    Operand lOperand;
    Operand rOperand;
    void checkCountSign() throws Exception {
        int count = 0;
        char[] enteredStrIsArray = this.enteredStr.toCharArray();

        for(char ch: enteredStrIsArray){
            if(ch == this.sign){
                count++;
                if(count > 1) { throw new Exception("Слишком много арифметических знаков!"); }
            }
        }
    }

    void checkSign() throws Exception {
        char[] s = {'-', '+', '*', '/'};
        for (byte i = 0; i < s.length; i++) {
            if (this.enteredStr.indexOf(s[i]) > 0) {
                if (this.sign == 0) {
                    this.sign = s[i];
                }
                else {
                    throw new Exception("Слишком много арифметических знаков!");
                }
            }
        }
        if (this.sign == 0) { throw new Exception("Арифметический знак указан не верно или не указан вообще!"); }
        checkCountSign();

    }

    void calcOperand() throws Exception {
        String s = (this.sign == '+') ? "\\+" : (this.sign == '*') ? "\\*" : String.valueOf(this.sign);

        String[] preOperand = this.enteredStr.split( s );
        if(preOperand.length == 2){
            if(preOperand[0].length() > 0 && preOperand[1].length() > 0) {
                this.lOperand = new Operand(preOperand[0]).checkValue();
                this.rOperand = new Operand(preOperand[1]).checkValue();
            } else {
                throw new Exception("Проверьте данные, над которыми производите вычисления!");
            }
        } else {
            throw new Exception("Арифметические операции возможны только для двух чисел!");
        }

        if(this.lOperand.isRoman != this.rOperand.isRoman) throw new Exception("Нельзя использовать сразу арабские и римские цифры!");

    }

    Operand run() throws Exception{
        int result;
        switch (this.sign){
            case '+':
                result = this.lOperand.asArabic() + this.rOperand.asArabic();
                break;
            case '-':
                result = this.lOperand.asArabic() - this.rOperand.asArabic();
                break;
            case '*':
                result = this.lOperand.asArabic() * this.rOperand.asArabic();
                break;
            case '/':
                result = this.lOperand.asArabic() / this.rOperand.asArabic();
                break;
            default:
                result = 0;
        }

        Operand resOperand = new Operand( result );
        resOperand.isRoman = lOperand.isRoman;

        if(resOperand.isRoman && resOperand.value < 1) throw new Exception("Римские цифры не могут быть меньше единицы!");

        return resOperand;
    }

    String go() throws Exception{
        checkSign();
        calcOperand();
        Operand result = run();

        return result.asAnswer();
    }
    Calc( String str){
        this.enteredStr = str;
    }
}
