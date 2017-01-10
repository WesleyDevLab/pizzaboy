package dev.ansuro.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Andy
 */
@Entity
public class Authority implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(length = 50, nullable = false, unique = true, updatable = false)
    private String name;

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Authority{" + "id=" + id + ", name=" + name + '}';
    }
}
