package pl.srslycpp.myWeb.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile file);


}
