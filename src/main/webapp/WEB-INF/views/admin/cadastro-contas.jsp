<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Biblioteca de Tags do Spring MVC -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Sistema contas - Cadastro contas</title>

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
			
				<h5>Cadastro de Contas</h5>
				<p>Preencha os campos para incluir uma conta.</p>
				<hr/>
				
				<div class="mb-2">
					<strong>${mensagem}</strong>
				</div>
				
				<form id="form_cadastro" action="cadastrar-conta" method="post">
				
					<div class="row mb-2">
						<div class="col-md-6">
							<label>Nome da conta:</label>
							<form:input path="model.nome" type="text" name="nome" id="nome" class="form-control"/>
						</div>
						<div class="col-md-3">
							<label>Valor da conta:</label>
							<form:input path="model.valor" type="text" name="valor" id="valor" class="form-control"/>
						</div>
						<div class="col-md-3">
							<label>Data da conta:</label>
							<form:input path="model.data" type="date" name="data" id="data" class="form-control"/>
						</div>
					</div>
					
					<div class="row mb-2">
						<div class="col-md-3">
							<label>Selecione o tipo da conta:</label>
							<div>
								<form:radiobutton path="model.tipo" name="tipo" value="1"/> Conta a receber
							</div>
							<div>
								<form:radiobutton path="model.tipo" name="tipo" value="2"/> Conta a pagar
							</div>
							<label for="tipo" class="error" style="display:none;"></label>
						</div>
						<div class="col-md-9">
							<label>Observações da conta:</label>
							<form:textarea path="model.observacoes" name="observacoes" id="observacoes" class="form-control"></form:textarea>
						</div>
					</div>
					
					<div class="row mb-2">
						<div class="col-md-12">
							<input type="submit" class="btn btn-success" value="Realizar Cadastro"/>
						</div>
					</div>
				
				</form>
			
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

			$("#form_cadastro").validate({
				rules: {
					'nome' : { required: true, minlength : 6, maxlength: 150 },
					'valor' : { required: true },
					'data' : { required: true },
					'tipo' : { required: true },
					'observacoes' : { required: true, minlength : 6, maxlength: 500 },
				}
			});
			
		})
	</script>
	
</body>
</html>