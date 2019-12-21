package nsc.ds;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("google")
public class GoogleCloudStorage implements DSInterface {
    private Storage storage = null;
    private static final String BUCKET_NAME = "fxkkc_storage";

    public GoogleCloudStorage(){
        try {
            File file = new ClassPathResource("credentials.json").getFile();
            Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(file));
            storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(MultipartFile multipartFile, String destinationPath) throws IOException {
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        Blob blob =
                storage.create(
                        BlobInfo.newBuilder(BUCKET_NAME, destinationPath).setAcl(acls).setContentType("image/png").build(),
                        multipartFile.getBytes());
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
