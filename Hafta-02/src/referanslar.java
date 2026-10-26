/*
================================================================================
JAVA BELLEK MODELİ: REFERANSLAR, ADRES KIYASI (==) VE İÇERİK KIYASI (.equals)
Dosya Adı: referanslar_ve_string_bellek_modeli.txt
Kaynak Dersi: Referans Tipler ve Bellek Yönetimi 
================================================================================
 */
public class referanslar 
{
    public static void main(String[] args) {
        String s1 = new String("Mustafa");
        String s2 = new String("Mustafa");

        // 1. DURUM: ADRES KIYASI
        if (s1 == s2) {
            System.out.println("Eşit");
        } else {
            System.out.println("Eşit Değil."); // Konsol çıktısı bu olur!
        }

        // 2. DURUM: İÇERİK KIYASI
        if (s1.equals(s2)) {
            System.out.println("Eşit");        // Konsol çıktısı bu olur!
        } else {
            System.out.println("Eşit Değil.");
        }
    }
    
}

/*
2. BELLEKTE (RAM) NELER DÖNÜYOR?
--------------------------------------------------------------------------------
Stack Bellek (Referanslar/İşaretçiler)       Heap Bellek (Gerçek Nesneler)
--------------------------------------       -----------------------------
[ s1 ] -------- (Adres: 0x100) ------------> [ Kutu 1: "Mustafa" ]
[ s2 ] -------- (Adres: 0x200) ------------> [ Kutu 2: "Mustafa" ]

- 'new' kelimesi her çağrıldığında Heap bellekte YENİ bir nesne (kutu) üretilir.
- İçerikleri harfi harfine aynı ("Mustafa") olsa dahi bellek adresleri 
  birbirinden tamamen bağımsızdır (0x100 != 0x200).

3. "==" İLE ".equals()" ARASINDAKİ MÜHENDİSLİK FARKI
--------------------------------------------------------------------------------
A) '==' Operatörü (Adres / Referans Kıyası):
   - Referans tiplerinde '==' nesnenin içeriğine bakmaz.
   - Sadece "Bu iki referans bellekte AYNI kutuyu mu gösteriyor?" sorusunu sorar.
   - s1 ve s2 iki ayrı nesneyi gösterdiği için:
     (s1 == s2) -> FALSE üretir -> "Eşit Değil."

B) '.equals()' Metodu (İçerik / Değer Kıyası):
   - java.lang.Object sınıfından gelen temel bir metottur.
   - String sınıfı bu metodu ezmiş (@Override etmiştir).
   - Adreslere bakmaz; kutuların içindeki metinleri karakter karakter karşılaştırır.
   - Her iki kutuda da "Mustafa" yazdığı için:
     s1.equals(s2) -> TRUE üretir -> "Eşit"

4. MÜHENDİSLİK PÜF NOKTASI: STRING POOL (HAVUZ) ETKİSİ
--------------------------------------------------------------------------------
Eğer kodu 'new' kullanmadan şöyle yazsaydık:

    String a = "Mustafa";
    String b = "Mustafa";

    if (a == b) -> TRUE DÖNERDİ!

NEDEN?
Java, bellek tasarrufu yapmak için Heap içinde "String Pool" (Metin Havuzu) tutar.
Çift tırnakla doğrudan metin yazdığında Java derleyicisi havuza bakar:
- "Mustafa" zaten havuzda varsa ikinci kez nesne üretmez.
- Hem 'a' hem de 'b' referansını havuzdaki TEK BİR nesneye bağlar.
- Dolayısıyla (a == b) adres kıyası da 'true' çıkar.

ANCAK 'new String(...)' yazdığın an Java'ya şu emri vermiş olursun:
"Havuzu boşver, bana Heap bellekte kesinlikle bağımsız yeni bir alan tahsis et!"
Bu yüzden 'new' ile üretilen nesnelerde '==' daima 'false' verir.

5. ALTIN KURAL (VİZE VE SEKTÖR İÇİN)
--------------------------------------------------------------------------------
1. İlkel veri tipleri (int, double, char, boolean) değer tutar -> '==' ile kıyaslanır.
2. Referans tipleri (String, Nesneler, Diziler) adres tutar -> DAİMA '.equals()' ile kıyaslanır!
3. Nesneleri '==' ile kıyaslamak en yaygın acemi hatası ve çalışma zamanı böceğidir (bug).
================================================================================
 */