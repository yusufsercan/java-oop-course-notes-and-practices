# 📌 Hafta 01 — Nesne Yönelimli Programlamaya Giriş ve Temel İlkeler

Bu modülde, prosedürel (yapısal) programlama alışkanlıklarından Nesne Yönelimli Programlama (OOP) paradigmasına geçiş yapılmış; sınıf-nesne ilişkisi, yapıcılar (`constructor`), model-sürücü sınıf mimarisi ve temel bellek davranışları ele alınmıştır.

---

## 🎯 Kazanımlar ve Odak Noktaları

- **Yapısal vs. Nesne Yönelimli Yaklaşım:** Fonksiyon/veri ayrımından, veriyi ve davranışı tek çatıda toplayan sınıf yapısına geçiş.
- **Model ve Sürücü (Demo) Mimarisi:** Sorumlulukların ayrılması ilkesi (*Separation of Concerns*) uyarınca `Main` metodunu model sınıflarının dışına (`*Demo.java`) çıkarma.
- **Yapıcı Metotlar (`Constructors`):** Nesnenin bellekte (Heap) tutarlı bir başlangıç durumuyla doğmasını garanti altına alma.
- **Kapsülleme Temelleri:** Alanların dış müdahalelere kapatılması ve anlamlı metotlarla kontrol edilmesi.

---

## 📂 Dosya İndeksi ve Konu Eşleştirmesi

| Dosya Adı | Açıklama / Ele Alınan Mühendislik Konusu |
| :--- | :--- |
| **`Merhaba.java`** | Temel Java sözdizimi, sınıf yapısı ve terminal akışı. |
| **`GirdiOrnegi.java`** | `Scanner` sınıfı ile konsoldan veri okuma ve tip yönetimi. |
| **`YapisalYaklasim.java`** | Prosedürel yaklaşımla banka hesabı simülasyonu (verilerin bağımsız değişkenlerde dağılması). |
| **`NesneYaklasimi.java`** | Veri ile davranışın bir araya getirildiği ilk OOP bankacılık adımı. |
| **`Hesap.java`** | Bakiye yönetimi, para çekme/yatırma iş kurallarını barındıran hesap modeli. |
| **`Ogrenci.java` & `OgrenciDemo.java`** | Öğrenci modeli; `this` anahtar kelimesi, kurucu metotlar ve sürücü sınıf ayrımı. |
| **`Dikdortgen.java` & `DikdortgenDemo.java`** | Alan ve çevre hesaplayan metotlar, veri bütünlüğü ve nesne davranışları. |
| **`Car.java` & `CarDemo.java`** | Yapıcı metotların kullanımı, nesne durumunun (state) incelenmesi. |

### 🧪 Laboratuvar Görevleri

| Laboratuvar Dosyası | Çözülen Problem / Görev |
| :--- | :--- |
| **`Gorev1_Kimlik.java`** | Konsol formatlama, string manipülasyonu ve kimlik doğrulama akışı. |
| **`Gorev2_NotIstatistik.java`** | Dizi işlemleri, istatistiksel hesaplamalar ve sınır değer kontrolleri. |
| **`Kitap.java` & `Gorev3_KitapDemo.java`** | Kitapçı simülasyonu; yapıcı aşırı yükleme (constructor overloading) ve nesne listeleme. |

---

## ⚙️ Derleme ve Çalıştırma

Tüm sınıflar terminal üzerinden `javac` ile `bin/` dizinine derlenip `java` ile çalıştırılabilir:

```bash
# Hafta-01 ana dizinindeyken:
# 1. Derleme (Örnek: OgrenciDemo)
javac -d ./bin ./src/Ogrenci.java ./src/OgrenciDemo.java

# 2. Çalıştırma
java -cp ./bin OgrenciDemo
