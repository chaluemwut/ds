package nsc.ds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Component
@PropertySource("classpath:application.properties")
public class DSService {
    @Autowired
    private Environment env;

    @Resource(name = "google")
    private DSInterface fsGoogle;

    @Resource(name = "native")
    private DSInterface fsNative;

    public void save(MultipartFile file, String path) throws IOException {
        DSInterface dynamicFsInterface = getFSInterface();
        dynamicFsInterface.save(file, path);
    }

    public byte[] get(String path) {
        DSInterface dynamicFsInterface = getFSInterface();
        return null;
    }

    private DSInterface getFSInterface() {
        DSInterface dynamicFsInterface = null;
        String fsName = env.getProperty("dynamicfs.name");
        if (fsName == null) {
            new RuntimeException("Please config dynamicfs.name in application.properties");
        }
        if ("google".equals(fsName)) {
            dynamicFsInterface = fsGoogle;
        } else if ("native".equals(fsName)) {
            dynamicFsInterface = fsNative;
        }
        return dynamicFsInterface;
    }

}
