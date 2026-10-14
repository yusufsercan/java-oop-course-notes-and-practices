import java.util.Scanner; // Scanner sinifini kullanabilmek icin import etmeliyiz ctrl+shift+o ile otomatik import edebiliriz

public class GirdiOrnegi 
{
     public static void main(String[] args)
     {
        Scanner tarayici = new Scanner  (System.in); // System.in ile klavyeden veri alacagimizi belirtiyoruz ve  Scanner sinifindan tarayici adinda bir nesne olusturuyoruz
            
        System.out.print("Adiniz: ");
        String ad = tarayici.nextLine(); // nextLine() metodu ile kullanicidan bir satirlik veri aliyoruz ve ad degiskenine atiyoruz

        System.out.print("Dogum yiliniz: ");
        int yil = tarayici.nextInt(); // nextInt() metodu ile kullanicidan bir tamsayi aliyoruz ve yil degiskenine atiyoruz

        int yas = 2026 - yil; // kullanicinin yasini hesapliyoruz
        System.out.printf("Merhaba %s, bu yil %d yasindasiniz.%n", ad, yas); // printf() metodu ile ekrana formatli bir sekilde yazdiriyoruz

        tarayici.close(); // Scanner nesnesini kapatiyoruz.
    
    }
}
 
// bu kodda Scanner sinifi kullanilarak kullanicidan veri alinir ve bu verilerle bir mesaj olusturulur. Scanner sinifi java.util paketinde bulunur ve kullanicidan veri almak icin kullanilir.
// Scanner taryici = new Scanner(System.in); bunu 1 kere yazmak yeterlidir, 
// Scanner sinifindan bir nesne olusturulur ve System.in ile klavyeden veri alacagimizi belirtiyoruz. Scanner sinifinin nextLine() metodu ile kullanicidan bir satirlik veri alinir,
//  nextInt() metodu ile kullanicidan bir tamsayi alinir. Bu verilerle kullanicinin yasini hesaplayip ekrana yazdiriyoruz. Son olarak Scanner nesnesini kapatiyoruz, bu iyi bir uygulamadir