import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFSFile;
import com.skypiea.client.AppClient;
import com.skypiea.common.utils.JsonUtils;
import com.skypiea.common.utils.TimeUtils;
import com.skypiea.system.model.GridFSFileInfo;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-21 13:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppClient.class)
public class MongoTest {

    //    @Autowired
//    MongoTemplate mongoTemplate;
//
    @Autowired
    GridFsTemplate gridFsTemplate;

    @Test
    public void testMongo() throws FileNotFoundException {

        File file = new File("/Users/huangwenjian/Desktop/1.jpeg");
        BasicDBObject metaData = new BasicDBObject("userId", "21341234");

        FileInputStream content = new FileInputStream(file);
        String fileName = file.getName();

        //获取文件类型
        int startPos = StringUtils.indexOf(fileName, ".");
        String contentType = StringUtils.substring(fileName, startPos + 1);
        GridFSFile gridFSFile = gridFsTemplate.store(content, fileName, contentType, metaData);

        GridFSFileInfo fileInfo = new GridFSFileInfo();
        fileInfo.set_id(gridFSFile.getId().toString());
        fileInfo.setContentType(gridFSFile.getContentType());
        fileInfo.setFilename(gridFSFile.getFilename());
        fileInfo.setLength(gridFSFile.getLength());
        fileInfo.setMd5(gridFSFile.getMD5());
        fileInfo.setMetaData(gridFSFile.getMetaData());
        fileInfo.setUploadTime(TimeUtils.date2String(gridFSFile.getUploadDate()));

        String json = JsonUtils.objectToJson(fileInfo);
        System.out.println(json);
    }
}
