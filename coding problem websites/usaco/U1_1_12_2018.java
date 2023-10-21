import java.io.*;
import java.util.*;
public class U1_1_12_2018 {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("mixmilk");
        int[] bm = new int[3];
        int[] b = new int[3];
        for (int i = 0; i < 3; i++) {
            bm[i] = io.nextInt();
            b[i] = io.nextInt();
        }
        int c = 0;
        ArrayList<Integer[]> bucketsFoundEarlier = new ArrayList<Integer[]>();
        //implement map so that you can go back to the bucket if found again and repeat those until done. saves time.
        while (c < 100) {
            
            int ob = c % 3;
            int ib = (ob + 1) % 3;
            //If the bucket that the milk is going into is too small to fit in all the milk
            if (bm[ib] - b[ib] < b[ob]) {
                int orb = b[ib];
                b[ib] = bm[ib];
                b[ob] -= bm[ib] - orb;
            }
            //Otherwise, the bucket that the milk is going into is big enough, and the contents of the other bucket gets fully put back in
            else {
                b[ib] += b[ob];
                b[ob] = 0;
            }
            c++;
        }
        for (int i = 0; i < 3; i++) {
            io.println(b[i]);
        }
        io.close();
    }
}
class Kattio extends PrintWriter {
    private BufferedReader r;
    private StringTokenizer st;
    // standard input
    public Kattio() { this(System.in, System.out); }
    public Kattio(InputStream i, OutputStream o) {
        super(o);
        r = new BufferedReader(new InputStreamReader(i));
    }
    // USACO-style file input
    public Kattio(String problemName) throws IOException {
        super(problemName + ".out");
        r = new BufferedReader(new FileReader(problemName + ".in"));
    }
    // returns null if no more input
    public String next() {
        try {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(r.readLine());
            return st.nextToken();
        } catch (Exception e) { }
        return null;
    }
    public int nextInt() { return Integer.parseInt(next()); }
    public double nextDouble() { return Double.parseDouble(next()); }
    public long nextLong() { return Long.parseLong(next()); }
}
