<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<head>
    <title><fmt:message key="menu.cliente.consultar"/></title>
    <meta name="menu" content="clientes"/>
</head>

<div class="col-sm-10">

    <h2><fmt:message key="menu.cliente.consultar"/></h2>

    <p><fmt:message key="cliente.mensaje.consulta"/></p>

    <div id="actions" class="btn-group">
        <a class="btn btn-primary btn-sm" href="<c:url value='/cliente/addCliente'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>

        <a class="btn btn-default btn-sm" href="<c:url value='/home'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <br />

    <display:table name="clientes" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover"
                   export="true">


        <display:column property="cuit" escapeXml="true" sortable="true" titleKey="Cuit" style="width: 25%"
                        paramId="id" paramProperty="id"/>
        <display:column property="razonSocial" escapeXml="true" sortable="true" titleKey="Nombre / Razon Social"
                        style="width: 25%"
                        paramId="id" paramProperty="id"/>
        <display:column property="direccion" escapeXml="true" sortable="true" titleKey="Dirección" style="width: 25%"
                        paramId="id" paramProperty="id"/>
        <display:column property="telefono" escapeXml="true" sortable="true" titleKey="Teléfono" style="width: 25%"
                        paramId="id" paramProperty="id"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="cliente.cliente"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="cliente.clientes"/></display:setProperty>
        <display:setProperty name="paging.banner.placement">none</display:setProperty>

        <display:setProperty name="export.excel.filename" value="User List.xls"/>
        <display:setProperty name="export.csv.filename" value="User List.csv"/>
        <display:setProperty name="export.pdf.filename" value="User List.pdf"/>


    </display:table>
</div>
