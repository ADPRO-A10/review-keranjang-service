package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import id.ac.ui.cs.advprog.reviewkeranjangservice.model.Keranjang;
import id.ac.ui.cs.advprog.reviewkeranjangservice.repository.KeranjangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeranjangServiceImpl implements KeranjangService{

    @Autowired
    private KeranjangRepository keranjangRepository;
    @Override
    public Keranjang createKeranjang(Keranjang keranjang) {
        Optional<Keranjang> result = keranjangRepository.findById(keranjang.getId());

        if (result.isEmpty()) {
            keranjangRepository.save(keranjang);
            return keranjang;
        }

        return null;
    }

    @Override
    public Keranjang findById(String keranjangId) {
        Optional<Keranjang> result = keranjangRepository.findById(keranjangId);

        return result.orElse(null);
    }
}
