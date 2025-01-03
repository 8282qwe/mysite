package mysite.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Optional;

@Service
public class FileUploadService {
    private static final String SAVE_PATH = "/Users/jangwoo/Desktop/[62]2024.11.포스코DX09/mysite/mysite-upload";
    private static final String URL = "/assets/upload-images";

    public String restore(MultipartFile file){
        try {
            File uploadDir = new File(SAVE_PATH);
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                return null;
            }

            if (file.isEmpty()) {
                return null;
            }

            String originalFileName = Optional.ofNullable(file.getOriginalFilename()).orElse("");
            String extName = originalFileName.substring(originalFileName.lastIndexOf("."));
            String saveFileName = generateSaveFilename(extName);
            System.out.println(saveFileName);
            long fileSize = file.getSize();

            byte[] data = file.getBytes();

            OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
            os.write(data);
            os.close();

            return URL + "/" + saveFileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateSaveFilename(String extName) {
        Calendar calendar = Calendar.getInstance();
        return "" +
                calendar.get(Calendar.YEAR) +
                calendar.get(Calendar.MONTH) +
                calendar.get(Calendar.DATE) +
                calendar.get(Calendar.HOUR) +
                calendar.get(Calendar.MINUTE) +
                calendar.get(Calendar.SECOND) +
                extName;
    }
}
