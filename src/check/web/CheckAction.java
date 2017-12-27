package check.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.UUID;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;


public class CheckAction {


    private OSSClient getOssClient() {
        String endpoint = OssProperty.getEndPoint();
        String accessKeyId = OssProperty.getAccessKeyId();
        String accessKeySecret = OssProperty.getAccessKeySecret();
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    //private List<File> file;
    private String fileFileName;
    private String fileFileContentType;
    private String filePath;
    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }


    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileFileContentType() {
        return fileFileContentType;
    }

    public void setFileFileContentType(String fileFileContentType) {
        this.fileFileContentType = fileFileContentType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    /**
     * 文件上传的方法
     */
    public String simpleFileupload() throws Exception {

        HttpServletRequest request=ServletActionContext.getRequest();
        HttpServletResponse response =ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        JSONObject result=new JSONObject();
        PrintWriter out = response.getWriter();


        File file = new File(fileFileName);
        // String path ="c://"+fileFileName;

        InputStream input = new FileInputStream(file);

        OSSClient ossClient = getOssClient();
        String vehicleBucket = OssProperty.getBucket();
        String folderName = "check/" + "file";

        String key = folderName + "/" + check.getReport_type() + "/" + UUID.randomUUID();


        try {

            ossClient.putObject(vehicleBucket, key, input);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
            result.put("resultCode", "600");
            out.print(result);
            return null;

        }
        String url = OssProperty.getHost() + "/" + key;


        result.put("resultCode", "200");

        out.print(result);
        return null;


    }
}



