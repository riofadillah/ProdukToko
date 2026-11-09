package kelas.model;

public class ProdukMakanan extends Produk {

    public ProdukMakanan(String kodeProduk, String namaProduk, int harga, String gambarUri) {
        super(kodeProduk, namaProduk, harga, gambarUri);
    }

    @Override
    public String getKategori() {
        return "Makanan";
    }
}