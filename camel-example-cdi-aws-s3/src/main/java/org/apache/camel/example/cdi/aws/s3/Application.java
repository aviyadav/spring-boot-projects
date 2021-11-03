package org.apache.camel.example.cdi.aws.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import java.io.File;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.processor.idempotent.FileIdempotentRepository;

public class Application {
    
    @ApplicationScoped
    static class AwsS3Route extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("aws-s3://bucket-name?deleteAfterRead=false&maxMessagesPerPoll=25&delay=5000")
                    .log(LoggingLevel.INFO, "consuming", "Consumer Fired!")
                    .idempotentConsumer(header("CamelAwsS3ETag"),
                            FileIdempotentRepository.fileIdempotentRepository(new File("target/file.data"), 250, 512000))
                    .log(LoggingLevel.INFO, "Replay Message Sent to file:s3out ${in.header.CamelAwsS3Key}")
                    .to("file:target/s3out?fileName=${in.header.CamelAwsS3Key}");
        }
        
        @Produces
        @Named("amazonS3Client")
        AmazonS3 amazonS3Client() {
            AWSCredentials credentials = new BasicAWSCredentials("", "");
            AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
            AmazonS3ClientBuilder clientBuilder = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(credentialsProvider);
            return clientBuilder.build();
        }
        
    }
}
