<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<head>
    <title><fmt:message key="menu.detalleOT.consultarOts"/></title>
    <meta name="menu" content=""/>
    <jsp:include page="../../common/headTag.jsp" />
</head>

<script type="text/javascript">
    $(document).ready(function (){

        $("#fechaCreacion").datepicker({format: "dd/mm/yyyy", weekStart: "0", language: 'es'});
        $("#cliente").val("${search.cliente.clienteId}");
        $("#estado").val("${search.estado}");


        $(document).on("click",".btnBaja", function () {

           var $form = $('<form id="darBajaForm"  action="darBaja" method="post"><input type="hidden" name="id" id="id" /></form>');
            $form.find("#id").val($(this).data("id"));
            $(document.body).append($form);
            $form.submit();

        });

        $(document).on("click",".btnAlta", function () {

            var $form = $('<form id="darAltaForm"  action="darAlta" method="post"><input type="hidden" name="id" id="id" /></form>');
            $form.find("#id").val($(this).data("id"));
            $(document.body).append($form);
            $form.submit();

        });


    });

</script>
<div class="col-sm-12">

    <h2><fmt:message key="ot.ot"/></h2>

    <p><fmt:message key="ot.mensaje.consulta"/></p>

    <div id="actions" class="btn-group">
        <a class="btn btn-primary btn-sm" href="<c:url value='/ot/addOT'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>

        <a class="btn btn-default btn-sm" href="<c:url value='/home'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>


    <form action="${ctx}/ot/search" method="get">
    <div class="row">
        <div class="col-sm-2">
            <label>Nro OT</label>
            <input value="${search.nrot}" name="nrot" class="form-control input-sm"/>
        </div>
        <div class="col-sm-2">
            <label>Estado</label>
            <select name="estado" id="estado" class="form-control input-sm">
                <option value="">Seleccionar</option>
                <c:forEach items="${estados}" var="estadosList">
                    <option value="${estadosList.codigo}">${estadosList.descripcion}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-sm-4">
            <label>Cliente</label>
            <select class="form-control input-sm" name="cliente.clienteId" id="cliente">
                <option value="">Seleccionar</option>
            <c:forEach items="${clientes}" var="clientesList">
                <option value="${clientesList.clienteId}">${clientesList.razonSocial}</option>
            </c:forEach>
            </select>
        </div>
        <div class="col-sm-2">
            <label>Fecha Creación</label>
            <input value="<fmt:formatDate pattern="dd/MM/yyyy" value="${search.fechaCreacion}" />" id="fechaCreacion"  name="fechaCreacion" class="form-control input-sm"/>
        </div>
        <div class="col-sm-2">
            <label>&nbsp;</label>
            <br />
            <button type="submit" class="btn btn-default btn-sm">Buscar</button>
        </div>
    </div>
    </form>


    <br />

    <display:table name="ots" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="ots" pagesize="25" class="table table-condensed table-striped table-hover"
                   export="true">


        <display:column property="nrot" escapeXml="true" sortable="true" titleKey="Nro Ot" style="width: 25%"
                        paramId="nrot" paramProperty="nrot" url="/items/search"/>
        <display:column property="cliente.razonSocial" escapeXml="true" sortable="true" titleKey="Cliente"
                        paramId="id" paramProperty="id"/>
        <display:column property="diashabiles" escapeXml="true" sortable="true" titleKey="Dias Hábiles"
                        style="width: 25%"
                        paramId="id" paramProperty="id"/>
        <display:column  escapeXml="true" sortable="true" titleKey="Fecha Alta"
                        paramId="id" paramProperty="id"><fmt:formatDate pattern="dd/MM/yyyy"  value="${ots.fAlta}" /></display:column>
        <display:column escapeXml="true" sortable="true" titleKey="Fecha Cierre"
                        paramId="id" paramProperty="id"><fmt:formatDate pattern="dd/MM/yyyy"  value="${ots.fCierre}" /></display:column>
        <display:column  escapeXml="true" sortable="true" titleKey="Estado"
                        paramId="id" paramProperty="id"><c:choose><c:when test="${ots.estado eq 'AC'}">Activa</c:when><c:otherwise>Inactiva</c:otherwise></c:choose></display:column>
        <display:column property="qinst" escapeXml="true" sortable="true" titleKey="Qinst"
                        paramId="id" paramProperty="id"/>
        <display:column property="qinstfaltan" escapeXml="true" sortable="true" titleKey="Qinstfaltan"
                        paramId="id" paramProperty="id"/>
        <display:column sortable="false" paramId="id" paramProperty="id" title="Acción">

        <c:choose>
        <c:when test="${ots.estado eq 'AC'}">
            <a href="#" data-id="${ots.otId}" class="btnBaja">Baja</a>
        </c:when>
        <c:otherwise>
            <a href="#" data-id="${ots.otId}" class="btnAlta">Alta</a>
        </c:otherwise>
        </c:choose>
        </display:column>


        <display:setProperty name="paging.banner.item_name"><fmt:message key="ot.ot"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="ot.ots"/></display:setProperty>
        <display:setProperty name="paging.banner.placement">none</display:setProperty>


        <display:setProperty name="export.excel.filename" value="User List.xls"/>
        <display:setProperty name="export.csv.filename" value="User List.csv"/>
        <display:setProperty name="export.pdf.filename" value="User List.pdf"/>


    </display:table>
</div>

