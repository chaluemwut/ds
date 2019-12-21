package nsc.ds;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DSInterface {

    public void save(MultipartFile multipartFile, String destinationPath) throws IOException;

    public void remove(String path);

    public boolean isExist(String path);

    public String getInfo(String path);

    public String getURL(String path);

}
