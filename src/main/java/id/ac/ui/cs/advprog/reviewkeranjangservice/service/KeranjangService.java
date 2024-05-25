package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;

import java.util.UUID;

public interface KeranjangService {
    public Keranjang createKeranjang(Keranjang keranjang);
    public Keranjang findById(UUID keranjangId);
    public void checkOut(UUID keranjangId);
}
