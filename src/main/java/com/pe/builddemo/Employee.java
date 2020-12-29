package com.pe.builddemo;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class Employee extends TableServiceEntity {
    private String name;
    private String email;
    private Integer age;

    public Employee(String partitionKey, String rowKey) {
        this.partitionKey = partitionKey;
        this.rowKey = rowKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
