package kelas.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kelas.Navigator;
import kelas.model.Produk;

public class DetailController {

    @FXML private ImageView imgProduk;
    @FXML private Label lblKode;
    @FXML private Label lblNama;
    @FXML private Label lblKategori;
    @FXML private Label lblHarga;

    private Navigator navigator;

    public void setData(Navigator navigator, Produk produk) {
        this.navigator = navigator;

        lblKode.setText(produk.getKodeProduk());
        lblNama.setText(produk.getNamaProduk());
        lblKategori.setText(produk.getKategori());
        lblHarga.setText(String.format("Rp %,d", produk.getHarga()));

        if (imgProduk != null) {
            if (produk.getGambarUri() != null && !produk.getGambarUri().isEmpty()) {
                imgProduk.setImage(new Image(produk.getGambarUri()));
            } else {
                imgProduk.setImage(null);
            }
        }
    }

    @FXML
    private void handleKembali() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}