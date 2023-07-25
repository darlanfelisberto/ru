package br.edu.iffar.relatorios;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.primefaces.model.StreamedContent;

import br.edu.iffar.fw.classBag.db.dao.UsuariosDAO;
import br.edu.iffar.fw.classBag.db.model.Usuario;
import br.edu.iffar.fw.classBag.util.MessagesUtil;
import br.edu.iffar.fw.classShared.constantes.InitConstantes;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.RollbackException;

@RequestScoped
public class CarteirinhaUtil {
	
	@Inject private UsuariosDAO usuariosDAO;
	@Inject private RelatoriosPath relatoriosPath;
	@Inject private MessagesUtil messagesUtil;
	
	public StreamedContent gerarCarterinhaQRCode(boolean nova,Usuario u) {
		try {
			if(nova) {
				u.setTokenRU(UUID.randomUUID().toString());
				usuariosDAO.updateT(u);
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("SUB_MY_QRCODE", RelatoriosPath.PATH_JAR_JASPER_SUB_MY_QRCODE);
		map.put("WHERE", " where u.usuario_id = '" + u.getMMId().toString() + "' ");
		map.put("PATH", InitConstantes.IMAGEM_PATH);
		map.put("EXTENSAO", InitConstantes.IMAGEM_EXTENSAO);
		try {
			return this.relatoriosPath.getJasper("carterinha_" + u.getCpf(), map, RelatoriosPath.JASPER_SUB_MY_QRCODE);
		} catch (Throwable e) {
			e.printStackTrace();
			this.messagesUtil.addError("Não foi possivel gerar a carterinha.", "Contate o admin do sistema.");
		}
		return null;
	}
}
