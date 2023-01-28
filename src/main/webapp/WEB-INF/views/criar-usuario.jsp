<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sistema contas - Cadastro de usuários</title>
	
	<!-- CDN da folha de estilos CSS do bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />

	<style>
		label.error { color: #df4759; }
		input.error { border: 2px solid #df4759; }
	</style>
	
</head>
<body>

	<div class="row">
		<div class="col-md-4 offset-md-4">
			<div class="card mt-3">
				<div class="card-body">
					
					<div class="text-center">
						<h2>Sistema de Contas</h2>
						<h5>Cadastro de usuários</h5>
					</div>
					
					<hr/>
	
					<form id="form_criarusuario">
	
						<div class="mb-2">
							<label>Entre com o seu nome:</label>
							<input type="text" name="nome" class="form-control"/>
						</div>
						
						<div class="mb-2">
							<label>Entre com o seu email:</label>
							<input type="text" name="email" class="form-control"/>
						</div>
		
						<div class="mb-2">
							<label>Entre com a sua senha</label>
							<input type="password" name="senha" id="senha" class="form-control"/>
						</div>
						
						<div class="mb-2">
							<label>Confirme a sua senha</label>
							<input type="password" name="senhaConfirmacao" class="form-control"/>
						</div>
		
						<div class="mb-2 d-grid">
							<input type="submit" value="Cadastrar" class="btn btn-primary"/>
						</div>
						
						<div class="mb-2 d-grid">
							<a href="/sistemacontas/" class="btn btn-light">
								Já possui cadastro? <strong>Acesse aqui!</strong>
							</a>
						</div>
	
					</form>	
					
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
			
			$("#form_criarusuario").validate({
				rules: {
					'nome' : { required: true, minlength: 8, maxlength: 150 },
					'email' : { required: true, email: true },
					'senha' : { required: true, minlength: 8, maxlength: 20 },
					'senhaConfirmacao' : { required: true, equalTo: '#senha' }
				}
			});
			
		})
	</script>
	
</body>
</html>