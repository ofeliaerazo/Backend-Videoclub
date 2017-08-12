package co.edu.sena.adsi.rest.utils;

import co.edu.sena.adsi.jpa.entities.EmailApp;
import co.edu.sena.adsi.jpa.entities.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author ruber19
 */
public class SendEmail {

    /**
     * Envia email al usuario en el momento del registro
     * @param configEmail
     * @param usuario
     * @param passwordAsignada 
     */
    public void sendEmailRegistroUsuario(EmailApp configEmail, Usuario usuario, String passwordAsignada) {
        try {

            HtmlEmail email = new HtmlEmail();
            email.setHostName(configEmail.getHostnameEmailApp());
            email.setSmtpPort(configEmail.getSmtpPort());
            email.setAuthenticator(new DefaultAuthenticator(configEmail.getEmailApp(), configEmail.getPasswordEmailApp()));
            if (configEmail.getIsAutentication()) {
                email.setAuthentication(configEmail.getEmailApp(), configEmail.getPasswordEmailApp());
            }
            email.setSSLOnConnect(configEmail.getIsSsl());
            email.setFrom(configEmail.getEmailApp());
            email.setCharset("UTF-8");
            email.setSubject("Bienvenido al ADSI.");

            StringBuilder sb = new StringBuilder();
            sb.append("<div><h1>Bienvenido al ADSI</h1>");
            sb.append("<h3 style='display:inline-block'>Email: </h3>");
            sb.append(usuario.getEmail());
            sb.append("    <h3 style='display:inline-block'>Password: </h3>");
            sb.append(passwordAsignada);
            sb.append("    <h3 style='display:inline-block'>Número de Identificación: </h3>");
            sb.append(usuario.getNumDocumento());
            sb.append("    <h3 style='display:inline-block'>Nombres: </h3>");
            sb.append(usuario.getNombres());
            sb.append("    <h3 style='display:inline-block'>Apellidos: </h3>");
            sb.append(usuario.getApellidos());
            sb.append("<p>Agradecemos su registro.</p>");
           
            email.setHtmlMsg(sb.toString());

            email.addTo(usuario.getEmail());
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
