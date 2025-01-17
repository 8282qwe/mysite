package mysite.service;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;

@Service
@PropertySource("classpath:mysite/config/web/fileupload.properties")
public class FileUploadService {

    private final Environment env;

    public FileUploadService(Environment env) {
        this.env = env;
    }

    public String restore(MultipartFile file){
        try {
            File uploadDir = new File(Objects.requireNonNull(env.getProperty("fileUpload.uploadLocation")));
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                return null;
            }

            if (file.isEmpty()) {
                return null;
            }

            String originalFileName = Optional.ofNullable(file.getOriginalFilename()).orElse("");
            String extName = originalFileName.substring(originalFileName.lastIndexOf("."));
            String saveFileName = generateSaveFilename(extName);

            long fileSize = file.getSize();

            byte[] data = file.getBytes();

            OutputStream os = new FileOutputStream(env.getProperty("fileUpload.uploadLocation") + "/" + saveFileName);
            os.write(data);
            os.close();

            return env.getProperty("fileUpload.resourceUrl") + "/" + saveFileName;
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
