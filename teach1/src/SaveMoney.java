public class SaveMoney {
    public static void main(String[] args){
        int days;
        double total=2.5;
        for(days=1;total<100;days++){
            if(days%5==0) {
                total = total - 6;
            }
                total=total+2.5;
        }
        System.out.println(total);
        System.out.println(days-1);
    }
}
