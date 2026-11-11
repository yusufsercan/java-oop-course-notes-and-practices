

public class Motor {

    private final int gucHp;
    private boolean calisiyor;

    public Motor(int gucHp) {

        if (gucHp <= 0) {
            throw new IllegalArgumentException("Motor gucu pozitif olmali.");
        }

        this.gucHp = gucHp;
        this.calisiyor = false;
    }

    public void calistir() {
        calisiyor = true;
        System.out.println("Motor calisti (" + gucHp + " HP)");
    }

    public void durdur() {
        calisiyor = false;
        System.out.println("Motor durdu");
    }

    public boolean calisiyorMu() {
        return calisiyor;
    }

    public int gucHp() {
        return gucHp;
    }

    public void yakitPompasiTest() {
        System.out.println("[servis] Yakit pompasi testi");
    }
}