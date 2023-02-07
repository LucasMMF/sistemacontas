<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Sistema contas - Consulta contas</title>

	<!-- CDN da folha de estilos CSS do bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
	
	<style>
		label.error { color: #df4759; }
		input.error, textarea.error { border: 2px solid #df4759; }
	</style>
	
</head>
<body>

	<!-- menu superior da aplicação -->
	<jsp:include page="/WEB-INF/views/admin/components/navbar.jsp"></jsp:include>
	
	<div class="container mt-3">
		
		<div class="card">
			<div class="card-body">
			
				<h5>Consultas de Contas</h5>
				<p>Utilize o filtro abaixo para consultar contas.</p>
				<hr/>
				
				<div class="mb-2">
					<strong>${mensagem}</strong>
				</div>
				
				<form id="form_consulta">
				
					<div class="row mb-2">
						<div class="col-md-3">
							<input type="date" id="dataIni" name="dataIni" class="form-control"/>
						</div>
						<div class="col-md-3">
							<input type="date" id="dataFim" name="dataFim" class="form-control"/>
						</div>
						<div class="col-md-6">
							<input type="submit" class="btn btn-success" value="Pesquisar Contas"/>
						</div>
					</div>
				
				</form>
				
				<div class="table-responsive mt-2">
					<table class="table table-sm table-hover">
						<thead>
							<tr>
								<th>Nome da conta</th>
								<th>Valor</th>
								<th>Data</th>
								<th>Tipo</th>
								<th>Operações</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<a href="/sistemacontas/admin/edicao-contas" class="btn btn-primary btn-sm">
										Editar
									</a>
									<a href="/sistemacontas/admin/exclusao-contas" class="btn btn-danger btn-sm">
										Excluir
									</a>
								</td>
							</tr>
						</tbody>
					</table>
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

			$("#form_consulta").validate({
				rules: {
					'dataIni' : { required: true },
					'dataFim' : { required: true }
				}
			});
			
		})
	</script>
	
</body>
</html>