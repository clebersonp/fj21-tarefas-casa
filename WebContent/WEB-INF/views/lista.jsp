<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<!-- 		no springmvc tem que criar um mapeamento de servlet no web.xml para olhar arquivos staticos na pasta static -->
		<script type="text/javascript" src="resources/js/jquery.js"></script>
		<script type="text/javascript" src="resources/js/date_format.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Listagem de Tarefas</title>
	</head>
	<body>
		
<!-- 		chama o servico no controller -->
		<a href="novaTarefa" >Cria nova tarefa</a>
		<br /> <br />
				
		<table border="1px solid #000" cellpadding="10" cellspacing="2" id="tabela">
			<tr>
				<th>ID</th>
				<th>DESCRIÇÃO</th>
				<th>FINALIZADO</th>
				<th>DATA FINALIZADO</th>
				<th>AÇÕES</th>
			</tr>
			
			<c:forEach var="tarefa" items="${tarefas}">
				<tr>
					<td>${tarefa.id}</td>
					<td>${tarefa.descricao}</td>
					<c:if test="${tarefa.finalizado eq true}">
						<td>Finalizado</td>
					</c:if>
					<c:if test="${tarefa.finalizado eq false}">
						<td id="tarefa_${tarefa.id}">Não Finalizado</td>
					</c:if>
					<td id="data_tarefa_${tarefa.id}" >
						<fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy"/>
					</td>
					<td>
						<a href="novaTarefa">Adicionar</a>
						<a href="removeTarefa?id=${tarefa.id}"> | Remover</a> 
						<a href="mostraTarefa?id=${tarefa.id}"> | Alterar</a> 
						<c:if test="${tarefa.finalizado eq false}">
							<a href="#" onclick="finalizaAgora(${tarefa.id})" id="botaoFinaliza"> | Finalizar Agora</a>
						</c:if> 
					</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<script type="text/javascript">
			function finalizaAgora(id) {
				console.log("Executando via ajax com jquery.");
				$.post("finalizaTarefa", {'id': id}, function(dadosDeResposta) {
					console.log("Mensagem de resposta: " + dadosDeResposta);
					alert("Tarefa Finalizada!");
				});
				
				$("#tarefa_" + id).html("Finalizado");
				$("#data_tarefa_" + id).html($.format.date(new Date(), "dd/MM/yyyy"));
				
				//removendo o link
				$("#botaoFinaliza").remove();
			};
			
			function finalizaAgoraComGet(id) {
				console.log("Executando via ajax com jquery.");
				$.get("finalizaTarefa?id=" + id, function(dadosDeResposta) {
					console.log("Mensagem de resposta: " + dadosDeResposta);
					alert("Tarefa Finalizada!");
				});
				
				$("#tarefa_" + id).html("Finalizado");
				$("#data_tarefa_" + id).html($.format.date(new Date(), "dd/MM/yyyy"));
				
				//removendo o link
				$("#botaoFinaliza").remove();
			}
		</script>
		
	</body>
</html>