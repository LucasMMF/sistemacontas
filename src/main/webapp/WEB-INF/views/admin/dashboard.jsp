<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width"/>
	
	<title>Sistema contas - Dashboard</title>

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
				<h5>Dashboard</h5>
				<p>Seja bem vindo ao sistema de gestão de contas.</p>
				<hr/>
				
				<div class="mb-2">
					<strong>${mensagem}</strong>
				</div>
				
				<form id="form_consulta" action="filtrar-dashboard" method="post">
				
					<div class="row mb-2">
						<div class="col-md-3">
							<form:input path="model.dataIni" type="date" id="dataIni" name="dataIni" class="form-control"/>
						</div>
						<div class="col-md-3">
							<form:input path="model.dataFim" type="date" id="dataFim" name="dataFim" class="form-control"/>
						</div>
						<div class="col-md-6">
							<input type="submit" class="btn btn-success" value="Filtrar Resultados"/>
						</div>
					</div>
				
				</form>
				
				<div class="row mt-2 mb-2">
					<div class="col-md-5">
						<div class="alert alert-success mb-2">
							<div class="row">
								<div class="col-md-8">
									Total de contas a receber:
								</div>
								<div class="col-md-4">
									<strong>
										<fmt:formatNumber value="${ total_contas_receber }" type="currency" currencyCode="BRL" />
									</strong>
								</div>
							</div>
						</div>
						<div class="alert alert-danger mb-2">
							<div class="row">
								<div class="col-md-8">
									Total de contas a pagar:
								</div>
								<div class="col-md-4">
									<strong>
										<fmt:formatNumber value="${ total_contas_pagar }" type="currency" currencyCode="BRL" />
									</strong>
								</div>
							</div>
						</div>
						<div class="alert alert-light mb-2">
							<div class="row">
								<div class="col-md-8">
									Saldo:
								</div>
								<div class="col-md-4">
									<strong>
										<fmt:formatNumber value="${ total_contas_receber - total_contas_pagar }" type="currency" currencyCode="BRL" />
									</strong>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-7">
						<div id="grafico"></div>
					</div>
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
	
	<!-- CDN dos arquivos da biblioteca Highcharts -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
	
	<script>
	
		$(document).ready(function() {
		        $("#form_consulta").validate({
		                rules: {
		                        'dataIni' : { required: true },
		                        'dataFim' : { required: true }
		                }
		        });
		        
		        var dados = [];
		        dados.push(['Total de Contas a Pagar', ${ total_contas_pagar }]);
		        dados.push(['Total de Contas a Receber', ${ total_contas_receber }]);
		        
		        new Highcharts.Chart({
		        	chart: { type: 'pie', renderTo: 'grafico' },
		        	title: { text: 'Total de contas a receber/pagar' },
		        	plotOptions: {
		        		pie: { innerSize: '60%' }
		        	},
		        	series: [ { data: dados } ],
		        	colors: ['#dc3545', '#198754']
		        });
		})
   	</script>
	
</body>
</html>