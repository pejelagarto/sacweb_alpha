<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<head>
    <title><fmt:message key="signup.title"/></title>
    <meta name="menu" content="clientes"/>
</head>

<div class="col-sm-2">
    <h2><fmt:message key="menu.cliente.nuevo"/></h2>

    <p><fmt:message key="menu.cliente.nuevo.message"/></p>
</div>
<div class="col-sm-7">


    <form:form commandName="cliente" method="post" action="nuevoCliente" id="nuevoClienteForm" autocomplete="off"
               cssClass="well">


        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">

            <div class="row">
                <div class="col-sm-12 form-group">
                    <appfuse:label styleClass="control-label" key="cliente.nombre"/>
                    <form:input cssClass="form-control input-sm" path="razonSocial" id="razonSocial"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 form-group">
                    <appfuse:label styleClass="control-label" key="cliente.cuit"/>
                    <form:input cssClass="form-control input-sm" path="cuit" id="cuit"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 form-group">
                    <appfuse:label styleClass="control-label" key="cliente.direccion"/>
                    <form:input cssClass="form-control input-sm" path="direccion" id="direccion"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 form-group">
                    <appfuse:label styleClass="control-label" key="cliente.telefono"/>
                    <form:input cssClass="form-control input-sm" path="telefono" id="telefono"/>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-sm" name="save" onclick="bCancel=false">
                    <i class="icon-ok icon-white"></i> <fmt:message key="button.continuar"/>
                </button>
            </div>

        </div>



    </form:form>





</div>