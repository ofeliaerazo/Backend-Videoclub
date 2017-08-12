package co.edu.sena.adsi.jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ruber28
 */
@Entity
@Table(name = "email_app")
@XmlRootElement
public class EmailApp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "email_app")
    private String emailApp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password_email_app")
    private String passwordEmailApp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descripcion_email_app")
    private String descripcionEmailApp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "hostname_email_app")
    private String hostnameEmailApp;
    @Column(name = "estado")
    private Boolean estado;
    
    @Column(name = "smtp_port")
    private int smtpPort;

    @Column(name = "is_autentication")
    private Boolean isAutentication;
    
    @Column(name = "is_ssl")
    private Boolean isSsl;

    public EmailApp() {
    }

    public EmailApp(String id) {
        this.id = id;
    }

    public EmailApp(String id, String emailApp, String passwordEmailApp, String descripcionEmailApp) {
        this.id = id;
        this.emailApp = emailApp;
        this.passwordEmailApp = passwordEmailApp;
        this.descripcionEmailApp = descripcionEmailApp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailApp() {
        return emailApp;
    }

    public void setEmailApp(String emailApp) {
        this.emailApp = emailApp;
    }

    public String getPasswordEmailApp() {
        return passwordEmailApp;
    }

    public void setPasswordEmailApp(String passwordEmailApp) {
        this.passwordEmailApp = passwordEmailApp;
    }

    public String getDescripcionEmailApp() {
        return descripcionEmailApp;
    }

    public void setDescripcionEmailApp(String descripcionEmailApp) {
        this.descripcionEmailApp = descripcionEmailApp;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public Boolean getIsAutentication() {
        return isAutentication;
    }

    public void setIsAutentication(Boolean isAutentication) {
        this.isAutentication = isAutentication;
    }

    public Boolean getIsSsl() {
        return isSsl;
    }

    public void setIsSsl(Boolean isSsl) {
        this.isSsl = isSsl;
    }

    @Override
    public String toString() {
        return "EmailApp{" + "idEmailApp=" + id + '}';
    }

    public String getHostnameEmailApp() {
        return hostnameEmailApp;
    }

    public void setHostnameEmailApp(String hostnameEmailApp) {
        this.hostnameEmailApp = hostnameEmailApp;
    }
    
}
