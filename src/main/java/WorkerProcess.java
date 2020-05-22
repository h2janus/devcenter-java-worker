import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WorkerProcess {
    public static void main(String[] args) {
        while(true) {
            test();
            try { 
                Thread.sleep(120000);
            }
            catch(InterruptedException e) {}
        }
    }

    private static void test() {
        try {
            final URL url = new URL("https://h2-rest-api.herokuapp.com/index.json");
            try (InputStream in = url.openStream()) {
                final byte[] data = readData(in);
                System.out.println(new String(data));
            }
        }
        catch(IOException ex) {
            System.out.println(ex);
        }
    }

    private static byte[] readData(final InputStream in) throws IOException {
        final ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        int nByte;
        final byte[] buff = new byte[4096];
        while((nByte = in.read(buff)) > 0) {
            baOut.write(buff,0, nByte);
        }
        final byte[] data = baOut.toByteArray();
        baOut.close();
        
        return data;
    }
}