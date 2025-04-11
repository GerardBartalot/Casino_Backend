package FondoRoyaleApplication.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;

@Service
public class ImageService {
    
    // Convierte imagen a Base64
    public String convertToBase64(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    // Valida el tipo y tamaño de la imagen (opcional)
    public boolean isValidImage(MultipartFile file) {
        return file != null && 
               file.getContentType() != null && 
               file.getContentType().startsWith("image/") &&
               file.getSize() < 2_000_000; // Límite de 2MB
    }
}