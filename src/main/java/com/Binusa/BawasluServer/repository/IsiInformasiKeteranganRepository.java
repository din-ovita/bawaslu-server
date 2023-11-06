package com.Binusa.BawasluServer.repository;

import com.Binusa.BawasluServer.model.IsiInformasiKeterangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsiInformasiKeteranganRepository extends JpaRepository<IsiInformasiKeterangan, Long> {
}

