# Katalog Produk Toko

Aplikasi desktop sederhana berbasis JavaFX untuk mencatat dan mengelola katalog produk toko (makanan dan minuman)



## Screenshots

| Katalog Produk | Form Tambah Produk | Detail Produk |
| :---: | :---: | :---: |
| ![Katalog Produk](Screenshots/Daftar.png) | ![Form Produk](Screenshots/Form.png) | ![Detail Produk](Screenshots/Detail.png) |



## Fitur

* **Katalog Produk (TilePane):** Menampilkan daftar produk dalam bentuk kartu (grid) yang memuat foto, nama produk, kategori, dan harga.
* **Filter & Pencarian:** Menyaring produk berdasarkan kategori (Makanan/Minuman) serta pencarian berdasarkan nama produk.
* **Form Input & Pemilihan Gambar:** Form untuk menambahkan produk baru dan memilih file gambar lokal (`.png`, `.jpg`, `.jpeg`) menggunakan `FileChooser`.
* **Halaman Detail:** Menampilkan informasi lengkap produk beserta tampilan gambar berukuran besar.
* **Penyimpanan In-Memory:** Menggunakan `ObservableList` untuk menyimpan data produk selama aplikasi berjalan.
