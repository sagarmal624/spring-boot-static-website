package com.pe.builddemo;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.File;
import java.io.FileInputStream;

public class UploadApplication {
    public static final String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=sagarandcompany;AccountKey=Da8g3snxA5EYbiQPEzTyTlVmkHYPvSy4rAi7MXvr5b8S6UszwSFj8a/3a00K3FnSTWfzNC3aLHoRKb5JOB351Q==;EndpointSuffix=core.windows.net";

    public static void main(String[] args) {
        try {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

            // Create the blob client.
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

            // Retrieve reference to a previously created container.
            CloudBlobContainer container = blobClient.getContainerReference("document");

            // Define the path to a local file.
            final String filePath = "C:\\Users\\sagshank\\Projects data\\DPMRAS\\appdemo\\src\\main\\java\\com\\pe\\builddemo\\demo.txt";

            // Create or overwrite the "myimage.jpg" blob with contents from a local file.
            CloudBlockBlob blob = container.getBlockBlobReference("demo.txt");
            File source = new File(filePath);
            blob.upload(new FileInputStream(source), source.length());
        } catch (Exception e) {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

}
