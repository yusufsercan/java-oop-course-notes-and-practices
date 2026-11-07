
public class YapiciZinciriDemo
{
    public static void main(String[] args)
    {
        System.out.println("=== 1. ORNEK: 3 PARAMETRE ===");

        KitapZinciri k1 = new KitapZinciri(
            "978-123", "Java OOP", "Ahmet"
        );

        System.out.println("Sonuc: " + k1);


        System.out.println("\n=== 2. ORNEK: 2 PARAMETRE ===");

        KitapZinciri k2 = new KitapZinciri(
            "978-124", "Veri Yapilari"
        );

        System.out.println("Sonuc: " + k2);


        System.out.println("\n=== 3. ORNEK: ANA CONSTRUCTOR ===");

        KitapZinciri k3 = new KitapZinciri(
            "978-125", "Algoritmalar", "Mehmet", 450, 2023
        );

        System.out.println("Sonuc: " + k3);


        System.out.println("\n=== 4. ORNEK: GECERSIZ VERI ===");

        try
        {
            KitapZinciri hatali = new KitapZinciri(
                "", "Hatali Kitap"
            );

            System.out.println(hatali);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("RED: " + e.getMessage());
        }
    }
}
