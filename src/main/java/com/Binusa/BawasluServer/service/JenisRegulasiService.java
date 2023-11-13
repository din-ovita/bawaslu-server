package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.DTO.JenisRegulasiDTO;
import com.Binusa.BawasluServer.model.JenisRegulasi;
import com.Binusa.BawasluServer.model.MenuRegulasi;
import com.Binusa.BawasluServer.model.Regulasi;
import com.Binusa.BawasluServer.repository.JenisRegulasiRepository;
import com.Binusa.BawasluServer.repository.MenuRegulasiRepository;
import com.Binusa.BawasluServer.repository.RegulasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JenisRegulasiService {
    @Autowired
    private JenisRegulasiRepository jenisRegulasiRepository;
    @Autowired
    private MenuRegulasiRepository menuRegulasiRepository;
    @Autowired
    private RegulasiRepository regulasiRepository;

    private long id;

    public Optional<JenisRegulasi> findById(Long id) {
        return Optional.ofNullable(jenisRegulasiRepository.findById(id));
    }

    public JenisRegulasi save(JenisRegulasiDTO jenisRegulasi){
        JenisRegulasi jenisRegulasi1 = new JenisRegulasi();
        jenisRegulasi1.setJenisRegulasi(jenisRegulasi.getJenisRegulasi());

        return jenisRegulasiRepository.save(jenisRegulasi1);
    }

    public List<JenisRegulasi> allJenisRegulasi() {
        List<JenisRegulasi> jenisRegulasis = new ArrayList<>();
        jenisRegulasiRepository.findAll().forEach(jenisRegulasis::add);
        return jenisRegulasis;
    }

    public JenisRegulasi getJenisRegulasiById(Long id) throws Exception{
        JenisRegulasi jenisRegulasi = jenisRegulasiRepository.findById(id);
        if (jenisRegulasi == null) throw new Exception("Jenis regulasi not found!!!");
        return jenisRegulasi;
    }

    public void delete(Long id) {
        JenisRegulasi jenisRegulasi = jenisRegulasiRepository.findById(id);
        if(jenisRegulasi != null) {
            List<MenuRegulasi> menuRegulasis = menuRegulasiRepository.getByJenis(jenisRegulasi.getId());

            for (MenuRegulasi menuRegulasi : menuRegulasis) {
                List<Regulasi> regulasiList = regulasiRepository.getByMenuRegulasi(menuRegulasi.getId());

                for(Regulasi regulasi : regulasiList) {
                    regulasiRepository.delete(regulasi);
                }

                menuRegulasiRepository.delete(menuRegulasi);

            }

            jenisRegulasiRepository.delete(jenisRegulasi);
        }
        jenisRegulasiRepository.delete(jenisRegulasi);
    }

//        public void delete(Long id) {
//        CategoryBerita categoryBerita = categoryBeritaRepository.getById(id);
//        if (categoryBerita != null) {
//            List<Berita> relatedBeritas = beritaRepository.getAllByCategory(categoryBerita.getId());
//
//            for (Berita berita : relatedBeritas) {
//                beritaRepository.delete(berita);
//            }
//
//            categoryBeritaRepository.delete(categoryBerita);
//        }
//        categoryBeritaRepository.delete(categoryBerita);
//    }

    public JenisRegulasi update(long id, JenisRegulasiDTO jenisRegulasi) throws Exception{
        JenisRegulasi jenisRegulasi1 = jenisRegulasiRepository.findById(id);
        jenisRegulasi1.setJenisRegulasi(jenisRegulasi.getJenisRegulasi());
        return jenisRegulasiRepository.save(jenisRegulasi1);
    }
}
