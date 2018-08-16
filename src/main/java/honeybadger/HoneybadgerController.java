package honeybadger;

import org.springframework.web.bind.annotation.RestController;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@RestController
public class HoneybadgerController {

    @Value("${aws.access.key}")
    private String key;
    
    @Value("${aws.secret}")
    private String secret;
    
    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "test route";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
    	return "test";
    }

}