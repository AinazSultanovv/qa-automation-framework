
//Модель тест-кейса
package org.qa.models;


public class TestCase {
    private String id;
    private String name;
    private TestStatus status;

    public TestCase(String id, String name, TestStatus status){
        this.id = id;
        this.name = name;
        status = TestStatus.IN_PROGRESS;
    }
    public String getId(){return id;};
    public String name(){return name;};
    public TestStatus getStatus(){return status;};

    public void setStatus(){
        this.status = status;
    };

    @Override
    public String toString(){
        return "TestCase{id='" + this.id + "', name=' " + this.name + "', status=" + this.status +"}";
    }

}
