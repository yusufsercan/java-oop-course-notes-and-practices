public class Gorev2_NotIstatistik 
{
    public static void main(String[] args)
    {
       int[] notlar = {68, 74, 91, 55, 80, 43, 97, 62};
       System.out.printf("Ortalama     :%.2f%n", ortalama(notlar));// notlar dizisinin başlangıç adresi fonksiyona gider bu sayede aşağıdaki fonkiyondaki dizi isimli değişken bizim notlar adresinde tutuğumuz sayıları erişip kullanabilir
       System.out.printf("En Yuksek    :%d%n",en_Buyuk(notlar));
       System.out.printf("En Dusuk     :%d%n",en_Dusuk(notlar));

    }
   
    public static double ortalama(int[] dizi)
    {
        int toplam=0;
        for(int notDegeri : dizi)
        {
            toplam += notDegeri;
        }
        return(double) toplam / dizi.length;
    }
    
    public static int en_Buyuk(int[] dizi)
    {
        int enb= dizi[0];
        for(int sayi : dizi)
        {
            if(sayi>enb)
            { // yani enb=dizi[0] yaptığımız için enb yi 0. indekse yani 68 e eşitlioyruz ondan sonra sayi 74 oluyor 74>68 den evet ozmn yeni enb=74 böyle böyle gidiyor
                enb=sayi;
            }
            
        }
        return enb;
    }
    
    public static int en_Dusuk(int[] dizi)
    {
        int enk=dizi[0];
        for(int sayi2 : dizi)
        {
            if(sayi2<enk)
            {
                enk=sayi2;
            }
        }
        return enk;
    }


    
}
 
/* 
int[] : Türü belirtir. Java'ya diyorsun ki: "Bu metoda dışarıdan tek bir sayı değil, bir tam sayı dizisi (tamsayılar vagonu) gelecek."
dizi : Bu metodun içindeki yerel etikettir (parametre adı).

for ( int notDegeri  :  dizi ) --> gelişmiş for
        │            │    └── [68, 74, 91, 55, 80, 43, 97, 62]
        │            └── "İÇİNDEKİ HER BİR"
        └── O an eline aldığın tek bir not

-> dizinin içindeki elemanları sırayla teker teker eline al. Her adımda o anki elemanı notDegeri adlı bir değişkene koy ve döngünün içine sok."

Arka planda ne oluyor?

1.Turda: notDegeri = 68 olur. toplam += 68 çalışır.

2.Turda: notDegeri = 74 olur. toplam += 74 çalışır.

3.Turda: notDegeri = 91 olur. toplam += 91 çalışır.

...

8.Turda: notDegeri = 62 olur. Dizi bittiği için döngü kendiliğinden durur.

Sen hiç i indisiyle, sayaçla, i < 8 şartıyla uğraşmazsın. Java senin yerine sırayla gezer.


YIĞIN (STACK)                                 ÖBEK (HEAP)
┌───────────────────────────┐               ┌─────────────────────────────────────┐
│ main() Çerçevesi          │               │                                     │
│   int[] notlar  ──────────┼──────────────►│  [Dizi Nesnesi - Adres: @0xABC]     │
└───────────────────────────┘               │  ─────────────────────────────────  │
                                            │  length = 8                         │
                                            │  [68, 74, 91, 55, 80, 43, 97, 62]   │
┌───────────────────────────┐               │                                     │
│ ortalama() Çerçevesi      │               │                                     │
│   int[] dizi    ──────────┼──────────────►│                                     │
└───────────────────────────┘               └─────────────────────────────────────┘


C Dilinde: Dizi dediğin yapı aslında bellekteki ilk elemanın adresini tutan çıplak bir işaretçiden (pointer) ibarettir. Dizi bellekte nerede bittiğini, boyunun kaç olduğunu kendisi bilmez. Fonksiyona o adresi gönderdiğinde dizinin sınırını aşmamak (Buffer Overflow yapmamak) için mecburen yanına bekçi olarak boyutu (int n) koymak zorundasındır.

Java'da: Diziler sıradan bir işaretçi değil, Öbek'te (Heap) yaşayan birinci sınıf birer nesnedir (Object). Java, diziyi oluşturduğu anda onun kimlik kartına değiştirilemez (read-only) bir .length alanı ekler. Dizi kendi boyutunu hafızasında zaten taşıdığı için, metotlara ayrıyeten int n parametresi göndermek gereksiz bir hamallıktır; doğrudan dizi.length denir.


'for (int not : notlar)' (For-Each) Hangi Durumlarda KULLANILAMAZ?
 * 
 * 1. Eleman Güncelleme (Yazma): 'not' değişkeni dizideki sayının geçici bir 
 *    kopyasıdır. Döngü içinde 'not += 5' yazmak dizideki asıl veriyi DEĞİŞTİRMEZ.
 * 2. İndis İhtiyacı: Döngüde sayaç (i) bulunmaz. Elemanın kaçıncı sırada olduğunu 
 *    bilmek veya aranan bir elemanın indeksini bulmak için kullanılamaz.
 * 3. Ters / Adımlı Gezinme: Döngü mecburen 0. indisten sonuncuya doğru ve birer 
 *    birer ilerler. Geriye doğru (i--) veya ikişer atlayarak (i += 2) gezemez.
 * 4. Paralel Dizi Gezme: Aynı anda iki farklı diziyi (örn. vize ve final dizilerini) 
 *    ortak bir indisle eşzamanlı okumak mümkün değildir.

*/