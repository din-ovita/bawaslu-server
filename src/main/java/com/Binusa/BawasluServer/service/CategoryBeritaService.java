package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.model.Berita;
import com.Binusa.BawasluServer.model.CategoryBerita;
import com.Binusa.BawasluServer.repository.CategoryBeritaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryBeritaService {
    @Autowired
    private CategoryBeritaRepository categoryBeritaRepository;
    private long id;

    public CategoryBeritaService() {
    }

    public CategoryBerita save(CategoryBerita categoryBerita) {
        CategoryBerita categoryBerita1 = new CategoryBerita();
        categoryBerita1.setCategory(categoryBerita.getCategory());
        return categoryBeritaRepository.save(categoryBerita1);
    }

    public Optional<CategoryBerita> findById(Long id) {
        return Optional.ofNullable(categoryBeritaRepository.findById(id));
    }

    public List<CategoryBerita> findAll() {
        List<CategoryBerita> categoryBerita = new ArrayList<>();
        categoryBeritaRepository.findAll().forEach(categoryBerita::add);
        return categoryBerita;
    }

    public void delete(Long id) {
        CategoryBerita categoryBerita = categoryBeritaRepository.findById(id);
        categoryBeritaRepository.delete(categoryBerita);
    }

    public CategoryBerita update(Long id, CategoryBerita categoryBerita) {
        CategoryBerita categoryBerita1 = categoryBeritaRepository.findById(id);
        categoryBerita1.setCategory(categoryBerita.getCategory());
        return categoryBeritaRepository.save(categoryBerita1);
    }

    public CategoryBerita getById(Long id) throws Exception{
        CategoryBerita categoryBerita = categoryBeritaRepository.findById(id);
        if (categoryBerita == null) throw new Exception("Category berita not found!!!");
        return categoryBerita;

    }

}