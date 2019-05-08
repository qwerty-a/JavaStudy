public class ForCycle_08 {
    public static void yh_Triangle(int array[][], int rows) { // 其作用是输出杨辉三角
        for (int i = 0; i <= rows; i++) { // 行循环
            for (int j = 0; j <= array[i].length - 1; j++) { 	// 列循环
                if (i == 0 || j == 0 || j == array[i].length - 1)
                    array[i][j] = 1; 				// 将两边元素设为1
                else
                    // 元素值为其正上方元素与左上角元素之和
                    array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
            }
        }
        for (int i = 0; i <= rows; i++) { 				// 行循环
            for (int j = 0; j <= array[i].length - 1; j++)	// 列循环

                System.out.print(array[i][j] + " "); 	// 输出数组元素
            System.out.println(); 				// 换行
        }
    }
    public static void main(String args[]) {
        final int rows = 7; 						// 设置行数
        int array[][] = new int[rows + 1][]; 			// 声明二维数组,行数为8
        for (int i = 0; i <= rows; i++) { 				// 循环初始化数组
            array[i] = new int[i + 1];
        }
        System.out.println("杨辉三角如下：");
        yh_Triangle(array, rows); 					// 调用方法显示杨辉三角
    }
}
