package kelas.model;

public class ProdukMinuman extends Produk {

    public ProdukMinuman(String kodeProduk, String namaProduk, int harga, String gambarUri) {
        super(kodeProduk, namaProduk, harga, gambarUri);
    }

    @Override
    public String getKategori() {
        return "Minuman";
    }
}