
public class Hesap 
{

    private static int sonrakiNo = 1001;

    private final String hesapNo;

    protected final String sahip;
    protected double bakiye;

    public Hesap(String sahip, double acilisBakiyesi) {

        if (sahip == null || sahip.isBlank()) {
            throw new IllegalArgumentException("Sahip bos olamaz.");
        }

        if (!Double.isFinite(acilisBakiyesi) ||
                acilisBakiyesi < 0) {
            throw new IllegalArgumentException("Gecersiz bakiye.");
        }

        this.hesapNo = "TR" + sonrakiNo++;
        this.sahip = sahip;
        this.bakiye = acilisBakiyesi;
    }

    public final String hesapNo() {
        return hesapNo;
    }

    public double getBakiye() {
        return bakiye;
    }

    public boolean yatir(double miktar) {

        if (!Double.isFinite(miktar) || miktar <= 0) {
            return false;
        }

        bakiye += miktar;
        return true;
    }

    public boolean cek(double miktar) {

        if (!Double.isFinite(miktar) || miktar <= 0) {
            return false;
        }

        if (miktar > bakiye) {
            return false;
        }

        bakiye -= miktar;
        return true;
    }

    protected double kesintiOrani() {
        return 0.0;
    }

    public double aylikKesinti() {
        return bakiye * kesintiOrani();
    }
}
