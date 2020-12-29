package com.pe.builddemo;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.queue.CloudQueue;
import com.microsoft.azure.storage.queue.CloudQueueClient;
import com.microsoft.azure.storage.queue.CloudQueueMessage;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class BuilddemoApplication extends SpringBootServletInitializer {
    Log logger = LogFactory.getLog(BuilddemoApplication.class);
    public static final String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=sagarandcompany;AccountKey=qePhNl9kDmns6NX1f2wzCO3Y6g4CaU2f040xxobgZd/u0NY+q9a2/p1+qNqxJqhJVX9CF0xTSKG5izOhIqfuXg==;EndpointSuffix=core.windows.net";

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BuilddemoApplication.class);
    }

    @GetMapping("api/v1/publish/msg")
    public String publishMsg() {
        try
        {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount =
                    CloudStorageAccount.parse(storageConnectionString);

            // Create the queue client.
            CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

            // Retrieve a reference to a queue.
            CloudQueue queue = queueClient.getQueueReference("notification");

            // Create the queue if it doesn't already exist.
            queue.createIfNotExists();

            // Create a message and add it to the queue.
            CloudQueueMessage message = new CloudQueueMessage("Hello, World");
            queue.addMessage(message);
        }
        catch (Exception e) {
            // Output the stack trace.
            e.printStackTrace();
        }
        return "Msg Published";
    }
    @GetMapping("C:\\Users\\sagshank\\Projects data\\DPMRAS\\SpringCloudFunctionWithAzure")
    public String peek() {
        try
        {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount =
                    CloudStorageAccount.parse(storageConnectionString);

            // Create the queue client.
            CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

            // Retrieve a reference to a queue.
            CloudQueue queue = queueClient.getQueueReference("notification");

            CloudQueueMessage peekedMessage = queue.retrieveMessage();

            // Output the message value.
            if (peekedMessage != null)
            {
                return peekedMessage.getMessageContentAsString();
            }

        }
        catch (Exception e) {
            // Output the stack trace.
            e.printStackTrace();
        }
        return "Msg Published";
    }
    @GetMapping("api/v1/msg")
    public String get(@RequestParam("name") String name, @RequestParam("email") String email) {
        try {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount =
                    CloudStorageAccount.parse(storageConnectionString);

            // Create the table client.
            CloudTableClient tableClient = storageAccount.createCloudTableClient();

            // Create a cloud table object for the table.
            CloudTable cloudTable = tableClient.getTableReference("employee");

            // Create a new customer entity.
            Employee customer1 = new Employee("employee", "2");
            customer1.setEmail(email);
            customer1.setName(name);

            // Create an operation to add the new customer to the people table.
            TableOperation insertCustomer1 = TableOperation.insertOrReplace(customer1);

            // Submit the operation to the table service.
            cloudTable.execute(insertCustomer1);
        } catch (Exception e) {
            // Output the stack trace.
            e.printStackTrace();
        }
        return "Hello calling from Controller";
    }

    public static void main(String[] args) {
        SpringApplication.run(BuilddemoApplication.class, args);
    }

}
