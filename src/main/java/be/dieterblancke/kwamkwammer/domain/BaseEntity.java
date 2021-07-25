package be.dieterblancke.kwamkwammer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable
{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected Long id;

    @JsonIgnore
    @CreatedDate
    @Column( name = "created_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Column( name = "updated_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate( Date createdDate )
    {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate()
    {
        return updatedDate;
    }

    public void setUpdatedDate( Date updatedDate )
    {
        this.updatedDate = updatedDate;
    }

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}
