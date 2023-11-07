package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.DTO.IsiInformasiKeteranganDTO;
import com.Binusa.BawasluServer.model.IsiInformasiKeterangan;
import com.Binusa.BawasluServer.response.CommonResponse;
import com.Binusa.BawasluServer.service.IsiInformasiKeteranganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bawaslu/api/isiinformasiketerangan")
@CrossOrigin(origins = "http://localhost:3000")
public class IsiInformasiKeteranganController {

    @Autowired
    private IsiInformasiKeteranganService isiInformasiKeteranganService;

    @PostMapping("/add")
    public ResponseEntity<CommonResponse<IsiInformasiKeteranganDTO>> createIsiInformasiKeterangan(@RequestBody IsiInformasiKeteranganDTO isiInformasiKeteranganDTO) {
        CommonResponse<IsiInformasiKeteranganDTO> response = new CommonResponse<>();
        try {
            IsiInformasiKeteranganDTO savedDTO = isiInformasiKeteranganService.save(isiInformasiKeteranganDTO);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(savedDTO);
            response.setMessage("Isi Informasi Keterangan created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create Isi Informasi Keterangan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<IsiInformasiKeteranganDTO>> getIsiInformasiKeterangan(@PathVariable("id") Long id) {
        CommonResponse<IsiInformasiKeteranganDTO> response = new CommonResponse<>();
        try {
            IsiInformasiKeteranganDTO isiInformasiKeteranganDTO = isiInformasiKeteranganService.findById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(isiInformasiKeteranganDTO);
            response.setMessage("Isi Informasi Keterangan retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve Isi Informasi Keterangan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<IsiInformasiKeteranganDTO>> updateIsiInformasiKeterangan(@PathVariable("id") Long id, @RequestBody IsiInformasiKeteranganDTO isiInformasiKeteranganDTO) {
        CommonResponse<IsiInformasiKeteranganDTO> response = new CommonResponse<>();
        try {
            IsiInformasiKeteranganDTO updatedDTO = isiInformasiKeteranganService.update(id, isiInformasiKeteranganDTO);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(updatedDTO);
            response.setMessage("Isi Informasi Keterangan updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update Isi Informasi Keterangan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> deleteIsiInformasiKeterangan(@PathVariable("id") Long id) {
        CommonResponse<String> response = new CommonResponse<>();
        try {
            isiInformasiKeteranganService.delete(id);
            response.setStatus("success");
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setData("Isi Informasi Keterangan deleted successfully.");
            response.setMessage("Isi Informasi Keterangan with id " + id + " deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to delete Isi Informasi Keterangan: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}