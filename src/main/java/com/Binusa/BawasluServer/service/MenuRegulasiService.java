package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.DTO.MenuRegulasiDTO;
import com.Binusa.BawasluServer.model.MenuRegulasi;
import com.Binusa.BawasluServer.model.Regulasi;
import com.Binusa.BawasluServer.repository.JenisRegulasiRepository;
import com.Binusa.BawasluServer.repository.MenuRegulasiRepository;
import com.Binusa.BawasluServer.repository.RegulasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuRegulasiService {
    @Autowired
    private MenuRegulasiRepository menuRegulasiRepository;
    @Autowired
    private JenisRegulasiRepository jenisRegulasiRepository;
    @Autowired
    private RegulasiRepository regulasiRepository;

    private long id;

    public MenuRegulasiService() {
    }

    public Optional<MenuRegulasi> findById(Long id) {
        return Optional.ofNullable(menuRegulasiRepository.findById(id));
    }

    public MenuRegulasi save(MenuRegulasiDTO menuRegulasiDTO) {
        MenuRegulasi menuRegulasi = new MenuRegulasi();
        menuRegulasi.setMenuRegulasi(menuRegulasiDTO.getMenuRegulasi());
        menuRegulasi.setJenisRegulasiId(jenisRegulasiRepository.findById(menuRegulasiDTO.getIdJenisRegulasi()));
        return menuRegulasiRepository.save(menuRegulasi);
    }

    public MenuRegulasi getById(Long id) throws Exception{
        MenuRegulasi menuRegulasi = menuRegulasiRepository.findById(id);
        if(menuRegulasi == null) throw new Exception("Menu regulasi not found!");
        return menuRegulasi;
    }

    public List<MenuRegulasi> allMenuRegulasi() {
        List<MenuRegulasi> menuRegulasis = new ArrayList<>();
        menuRegulasiRepository.findAll().forEach(menuRegulasis::add);
        return menuRegulasis;
    }

    // Metode untuk mendukung paginasi
    public Page<MenuRegulasi> allMenuRegulasiWithPagination(int page, int size) {
        return menuRegulasiRepository.getAllMenuRegulasi(PageRequest.of(page, size));
    }

    @Transactional
    public void delete(Long id) {
        MenuRegulasi menuRegulasi = menuRegulasiRepository.findById(id);
        if(menuRegulasi != null) {
            menuRegulasi.setJenisRegulasiId(null);
            List<Regulasi> regulasiList = regulasiRepository.getByMenuRegulasi(menuRegulasi.getId());

            for(Regulasi regulasi : regulasiList) {
                regulasiRepository.delete(regulasi);
            }

            menuRegulasiRepository.delete(menuRegulasi);
        }
        menuRegulasiRepository.delete(menuRegulasi);
    }

    public MenuRegulasi update(Long id, MenuRegulasiDTO menuRegulasiDTO) {
        MenuRegulasi menuRegulasi = menuRegulasiRepository.findById(id);
        menuRegulasi.setMenuRegulasi(menuRegulasiDTO.getMenuRegulasi());
        menuRegulasi.setJenisRegulasiId(jenisRegulasiRepository.findById(menuRegulasiDTO.getIdJenisRegulasi()));
        return menuRegulasiRepository.save(menuRegulasi);
    }

    public List<MenuRegulasi> allByJenisRegulasi(Long id) {
        return menuRegulasiRepository.getByJenis(id);
    }

    // Metode yang mendukung paginasi
    public Page<MenuRegulasi> allByJenisRegulasiWithPagination(Long id, int page, int size) {
        return menuRegulasiRepository.getByJenis(id, PageRequest.of(page, size));
    }
}
