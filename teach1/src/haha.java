public class haha{
    public static void main(String[] args) {
        int sum = 100;					// 最终的目标
        double db = 2.5;				// 每次存放的钱数
        int day = 1;					// 天数
        double dsum = 0;				// 每次存放的总数
        while (true) {
            dsum = dsum + db;			// 返回每天都存放钱的总和
            if (day % 5 == 0) {			// 判断是不是5的倍数
                dsum = dsum - 6;		// 从总数中扣去6元
                System.out.println("第" + day + "天花去6元，还剩" + dsum + "元！");
            }
            boolean flag = dsum >= sum;	// 求dsum是否大于sum的boolean值
            if (flag) {					// 如果是true则输出最终的结果，并跳出循环
                System.out.println("要经过连续存储" + day + "天，才能存上100元！");
                break;
            } else {					// 否则天数加1，也就是继续存钱
                day++;
            }
        }
    }
}