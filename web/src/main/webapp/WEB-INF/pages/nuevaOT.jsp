<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
    <meta name="menu" content="ot"/>
</head>


<div class="col-sm-2">
    <h2><fmt:message key="menu.detalleOt.nuevaOt"/></h2>

    <p><fmt:message key="detalleOt.nuevaOt.message"/></p>
</div>
<div class="col-sm-7">

    <form:form commandName="ot" method="post" action="crearOT" id="nuevaOtForm" autocomplete="off"
               cssClass="well">

        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            <div class="row">
                <div class="col-sm-3 form-group">
                    <appfuse:label styleClass="control-label" key="ot.nroOt"/> *
                    <form:input cssClass="form-control input-sm" path="nrot" id="nrot"/>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12 form-group">
                    <appfuse:label styleClass="control-label" key="ot.cliente"/> *
                    <form:select cssClass="form-control input-sm" path="cliente.clienteId">
                        <form:option value="">Seleccionar</form:option>
                        <c:forEach items="${clientes}" var="clientesList">
                            <form:option value="${clientesList.clienteId}">${clientesList.razonSocial}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>

            <div class="row">

                <div class="col-sm-12 form-group">
                    <appfuse:label styleClass="control-label" key="ot.diashabiles"/> *
                    <form:input cssClass="form-control input-sm" path="diashabiles" id="diashabiles"/>
                </div>

            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group text-left">
     <%--               <button href="/home" class="btn btn-primary btn-sm" name="volver">
                        <i class="icon-ok icon-white"></i> << <fmt:message key="button.volver"/>
                    </button>--%>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group text-right">
                    <button type="submit" class="btn btn-success btn-sm" name="continuar">
                        <i class="icon-ok icon-white"></i> <fmt:message key="button.continuar"/> >>
                    </button>
                </div>
            </div>
            </div>
    </form:form>
</div>

