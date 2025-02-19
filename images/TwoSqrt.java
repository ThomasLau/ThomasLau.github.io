public class TwoSqrt {
    final static double MM = 2855;
    static int NN = 1_0000_0000;
    static double s3 = 0;
    static long start3 = 0, start4 = 0;
    // static double j = 0;
    // static int jj = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            long d =0;
            // s3 = 0;//j=0;
            // long d = DNO();
            // System.out.println(String.format("DNO:%.15e\t%.3f", s3, d/1e3));
            // s3 = 0;//j=0;
            // d = D();
            // System.out.println(String.format("D  :%.15e\t%.3f", s3, d/1e3));
            s3 = 0;//j=0;
            d = DD();
            System.out.println(String.format("DD :%.15e\t%.3f", s3, d/1e3));
            s3 = 0;
            d = DI();
            System.out.println(String.format("DI :%.15e\t%.3f", s3, d/1e3));
            s3 = 0;
            d = DL();
            System.out.println(String.format("DL :%.15e\t%.3f", s3, d/1e3));
            s3 = 0;
            d = DIJ();
            System.out.println(String.format("DIJ:%.15e\t%.3f", s3, d/1e3));
            s3 = 0;
            d = II();
            System.out.println(String.format("II :%.15e\t%.3f", s3, d/1e3));
            System.out.println("------------");
        }
       
    }
    static long DNO() {
        long start3 = System.nanoTime();
        for (int i = 0; i < NN; i++) {
            s3 += i;
        }
        long start4 = System.nanoTime();
        return start4 - start3;
      }
   
    static long D() {
      long start3 = System.nanoTime();
      for (int i = 0; i < NN; i++) {
          s3 += Math.sqrt(MM);//0.1;
      }
      long start4 = System.nanoTime();
      return start4 - start3;
        // double s3=0;
//        long start3 = System.nanoTime();
//        for (int i = 0; i < NN; i++) {
//            s3 += Math.sqrt(MM);//0.1;
//        }
//        long start4 = System.nanoTime();
//        System.out.println(s3+"\t\t"+(start4 - start3)/1e3);
//        return (int)s3;// start4 - start3;
    }

    static long DD() {
        double j = 0;
        long start3 = System.nanoTime();
        for (int i = 0; i < NN; i++) {
            s3 += Math.sqrt(MM + j++);
        }
        long start4 = System.nanoTime();
        return start4 - start3;
    }

    static long DI() {
        long start3 = System.nanoTime();
        for (int i = 0; i < NN; i++) {
            s3 += Math.sqrt(MM + i);
        }
        long start4 = System.nanoTime();
        return start4 - start3;
    }
    static long DL() {
        long j = 0;
        long start3 = System.nanoTime();
        for (int i = 0; i < NN; i++) {
            s3 += Math.sqrt(MM + j++);
        }
        long start4 = System.nanoTime();
        return start4 - start3;
    }
    static long DIJ() {
        int jj=0;
        long start3 = System.nanoTime();
        for (int i = 0; i < NN; i++) {
            s3 += Math.sqrt(MM + jj++);
        }
        long start4 = System.nanoTime();
        return start4 - start3;
    }
    static long II() {
        long start3 = System.nanoTime();
        for (int i = 0; i < NN; i++) {
            s3 += Math.sqrt(285 + i);
        }
        long start4 = System.nanoTime();
        return start4 - start3;
    }
}
