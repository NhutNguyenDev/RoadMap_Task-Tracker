import java.time.LocalDateTime;

public class task {
    private int id;
    private String description;
    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    
    public task(int id, String description, String status, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    

    @Override
    public String toString() {
        return status + " - id " + id + ": " + description  + "\n       Created at: " + createAt
                + "\n       Last update:" + updateAt ;
    }


    public LocalDateTime getCreateAt() {
        return createAt;
    }



    
    

    
}
