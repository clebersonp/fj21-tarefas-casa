<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Adicionando a tag do spring para mostrar mensagens de erro -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Adicionar Tarefas</title>
	</head>
	<body>
		<h3>Adicionar tarefas</h3>
		
<!-- 		a acao chamada de adicionaTarefa sera um acao(metodo) em algum controller do spring -->
		
<!-- 			indicando qual atributo a mensagem está relacionada, ele pega o campo tarefa.descricao do bindingResult -->
		<form:errors path="tarefa.descricao" cssStyle="color: red" /><br />			
		<form action="adicionaTarefa" method="post">
			<label for="descricao">Descrição: </label><br />
			<textarea id="descricao" name="descricao" rows="5" cols="100"></textarea><br />
			
			<input type="submit" value="Adicionar" />
		</form>
	</body>
</html>