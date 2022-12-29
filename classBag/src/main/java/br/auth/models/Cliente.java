package br.auth.models;

import java.net.URI;
import java.net.URISyntaxException;

import br.auth.oidc.ResponseTypeEnum;
import br.edu.iffar.fw.classBag.db.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente", schema = "auth")
public class Cliente extends Model<Integer> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", insertable = false)
    private Integer idCliente;

    private String nome;

    private String redirecionamento;

    private String secret;

    @Column(name = "cliente_url")
    private String clienteUrl;

    @Column(name = "response_type")
    @Enumerated(EnumType.STRING)
    private ResponseTypeEnum responseType;

    @Override
    public Integer getMMId() {
        return this.idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRedirecionamento() {
        return redirecionamento;
    }

    public URI urlRedirecionamento(String redirect) throws URISyntaxException {
        URI ur = new URI(getRedirecionamento() + redirect.replaceAll(getRedirecionamento(), ""));
        System.out.println(ur.toString());
        return ur;
    }

    public void setRedirecionamento(String redirecionamento) {
        this.redirecionamento = redirecionamento;
    }

    public String getClienteUrl() {
        return clienteUrl;
    }

    public void setClienteUrl(String clienteUrl) {
        this.clienteUrl = clienteUrl;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public ResponseTypeEnum getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseTypeEnum responseType) {
        this.responseType = responseType;
    }
}
