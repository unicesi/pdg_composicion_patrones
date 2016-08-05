<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>TEST AUTHENTICATOR</title>
</head>
<body>
	<f:view>
		<h1>TEST AUTHENTICATOR</h1>

		<form action="./TestAuthenticator" method="post">
			<button type="submit">TestAuthenticator</button>
		</form>

		<form action="./TestAuthorizator" method="post">
			<button type="submit">TestAuthorizator</button>
		</form>
	</f:view>
</body>
</html>