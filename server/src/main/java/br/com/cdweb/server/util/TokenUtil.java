package br.com.cdweb.server.util;

import java.util.List;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.cdweb.persistence.domain.ModeloAcao;
import br.com.cdweb.persistence.domain.PerfilAcesso;
import br.com.cdweb.persistence.domain.Token;
import br.com.cdweb.persistence.domain.UsuarioPerfil;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.persistence.vo.FieldValuesVo;
import br.com.cdweb.persistence.vo.ResultFilterVo;
import br.com.cdweb.server.startup.ControlAccessBean;

public class TokenUtil {

	private ControlAccessBean bean = new ControlAccessBean();

	public Response.Status validateTokenAccessAction(String authorizationHeader, long idActionModel) {
		String token = extractToken(authorizationHeader);
		try {
			validateToken(token);
			ResultFilterVo<Token> resultFilterVo = JpaAllEntities.doFilter(Token.class,
					new FieldValuesVo("generatedToken", token));
			if (resultFilterVo != null && resultFilterVo.getResultQuery() != null
					&& resultFilterVo.getResultQuery().size() > 0) {
				Token tokenDomain = resultFilterVo.getResultQuery().get(0);
				ResultFilterVo<UsuarioPerfil> resultUserPerfil = JpaAllEntities.doFilter(UsuarioPerfil.class,
						new FieldValuesVo("usuario", tokenDomain.getUsuario()));
				if (resultUserPerfil != null && resultUserPerfil.getResultQuery() != null
						&& resultUserPerfil.getResultQuery().size() > 0) {
					List<UsuarioPerfil> usuarioPerfils = resultUserPerfil.getResultQuery();
					boolean hasAccess = false;
					for (UsuarioPerfil usuarioPerfil : usuarioPerfils) {
						if (usuarioPerfil.getPerfil().getPerfisAcesso() == null
								|| usuarioPerfil.getPerfil().getPerfisAcesso().size() > 0) {
							hasAccess = true;
							break;
						}
						ModeloAcao modeloAcao = JpaAllEntities.findById(idActionModel, ModeloAcao.class);
						for (PerfilAcesso perfilAcesso : usuarioPerfil.getPerfil().getPerfisAcesso()) {
							if (perfilAcesso.getModeloDispositivo().getIdModeloDispositivo() == modeloAcao
									.getModeloDispositivo().getIdModeloDispositivo()) {
								hasAccess = true;
								break;
							}
						}

					}
					if (hasAccess) {
						return Status.OK;
					}
				} else {
					throw new Exception("Usuario não possui perfil cadastrado");
				}

			} else {
				throw new Exception("Token registrado");
			}

			return Response.Status.OK;

		} catch (Exception e) {
			e.printStackTrace();
			return Response.Status.UNAUTHORIZED;
		}
	}

	public Response.Status validateTokenAccess(String authorizationHeader) {
		String token = extractToken(authorizationHeader);

		try {

			// Validate the token
			validateToken(token);

			return Response.Status.OK;

		} catch (Exception e) {
			return Response.Status.UNAUTHORIZED;
		}
	}

	private String extractToken(String authorizationHeader) {
		// Check if the HTTP Authorization header is present and formatted
		// correctly
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}

		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer ".length()).trim();
		return token;
	}

	private void validateToken(String token) throws Exception {
		// Check if it was issued by the server and if it's not expired
		// Throw an Exception if the token is invalid
		if (!bean.tokenIsValid(token)) {
			throw new Exception("ERRO TOKEN");
		}
	}

}
