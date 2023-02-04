<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Sistema contas - Dados do usuário</title>

	<!-- CDN da folha de estilos CSS do bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
	
	<style>
		label.error { color: #df4759; }
		input.error { border: 2px solid #df4759; }
	</style>
	
</head>
<body>

	<!-- menu superior da aplicação -->
	<jsp:include page="/WEB-INF/views/admin/components/navbar.jsp"></jsp:include>
	
	<div class="container mt-3">
	
		<div class="card">
			<div class="card-body">
				<h5>Dados do usuário</h5>
				<div>Nome do usuário: <strong>${usuario.nome}</strong></div>
				<div>Email de acesso: <strong>${usuario.email}</strong></div>
			</div>
		</div>
		
		<div class="card mt-2">
			<div class="card-body">
				<h5>Alterar senha de acesso</h5>
				
				<form id="form_alterar_senha" action="atualizar-senha" method="post">
				
					<div class="row mb-2">
						<div class="col-md-3">
							<label>Nova senha: </label>
							<form:input path="model.novaSenha" type="password" name="novaSenha" id="novaSenha" class="form-control"/>
						</div>
						<div class="col-md-3">
							<label>Confirme a nova senha: </label>
							<input type="password" name="novaSenhaConfirmacao" id="novaSenhaConfirmacao" class="form-control"/>
						</div>
					</div>
					
					<div class="row mb-2">
						<div class="col-md-6">
							<input type="submit" class="btn btn-success" value="Salvar alterações"/>
						</div>
					</div>
				
				</form>
				
				<div class="mt-2 text-success">
					<strong>${mensagem_sucesso}</strong>
				</div>
				
				<div class="mt-2 text-danger">
					<strong>${mensagem_erro}</strong>
				</div>
				
			</div>
		</div>
		
	</div>

	<!-- CDN do arquivo javascript do bootstrap -->	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- CDN do arquivo javascript do JQuery -->
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
	
	<!-- CDN dos arquivos da biblioteca JQuery Validation -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/additional-methods.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/localization/messages_pt_BR.min.js"></script>
	
	<script>
		$(document).ready(function() {
			
			$("#form_alterar_senha").validate({
				rules: {
					'novaSenha' : { required: true, minlength: 8, maxlength: 20 },
					'novaSenhaConfirmacao' : { required: true, equalTo: '#novaSenha' }
				}
			});
			
		})
	</script>
	
</body>
</html>