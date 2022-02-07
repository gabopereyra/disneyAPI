package com.api.disney.servicios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GuardadoArchivo {

    @Value("${web.upload-path}")
    private String carpetaGuardado;

    public void guardarArchivo(MultipartFile archivo) throws IOException{
        if(!archivo.isEmpty()){
            byte[] bytes = archivo.getBytes();
            Path path = Paths.get(carpetaGuardado + archivo.getOriginalFilename());
            Files.write(path, bytes);
        }
    }
}
