<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Listagem de Tarefas</title>
	</head>
	<body>
		
<!-- 		chama o servico no controller -->
		<a href="novaTarefa" >Cria nova tarefa</a>
		<br /> <br />
				
		<table border="1px solid #000" cellpadding="10" cellspacing="2">
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
						<td>Não FInalizado</td>
					</c:if>
					<td>
						<fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy"/>
					</td>
					<td>
						<a href="novaTarefa">Adicionar</a> | 
						<a href="removeTarefa?id=${tarefa.id}">Remover</a> | 
						<a href="mostraTarefa?id=${tarefa.id}">Alterar</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		
	</body>
</html>