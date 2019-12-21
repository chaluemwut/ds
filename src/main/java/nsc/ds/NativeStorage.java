package nsc.ds;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component("native")
public class NativeStorage implements DSInterface {

    @Override
    public void save(MultipartFile multipartFile, String destinationPath) throws IOException {

    }

    @Override
    public void remove(String path) {

    }

    @Override
    public boolean isExist(String path) {
        return false;
    }

    @Override
    public String getInfo(String path) {
        return null;
    }

    @Override
    public String getURL(String path) {
        return null;
    }
}
