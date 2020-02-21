package bean;

import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("user")
@ViewScoped
public class User implements Serializable {
    private String name;

    @PostConstruct
    public void init() {
        System.out.println("User.init()");
        setName("Lin");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
