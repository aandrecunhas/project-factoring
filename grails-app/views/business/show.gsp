<%@ page import="test.project.Bill" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'business.label', default: 'Empresa')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-business" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <br/>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="crud-bill" class="content scaffold-show">
            <h1>Calcule sua taxa de antecipação final</h1>
            <g:form action="saveBill">
                <g:hiddenField name="business.id" value="${business.id}"/>
                <div class="panel">
                    <div class="panel-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-3">
                                    <label for="originalValue">Valor Original</label>
                                    <g:textField name="originalValue" type="number" class="form-control money"
                                                 required=""  autocomplete="off"/>

                                </div>
                                <div class="col-md-3">
                                    <label for="daysToPay">Prazo</label>
                                    <g:textField name="daysToPay" type="number" class="form-control small_number"
                                                 required=""  autocomplete="off"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-3">
                                    <button type="submit" class="btn btn-primary btn-block">Calcular</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel">
                    <div class="panel-body">
                        <table class="table-striped">
                            <thead>
                                <tr>
                                    <td>Valor Original</td>
                                    <td>Prazo</td>
                                    <td>Taxa Final</td>
                                    <td>Valor A Receber</td>
                                    <td>Desconto</td>
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${Bill.list()}" var="bill">
                                    <tr>
                                        <td><g:formatNumber number="${bill.originalValue}" type="currency"/></td>
                                        <td>${bill.daysToPay}</td>
                                        <td><g:formatNumber number="${bill.factoringRate / 100}" type="percent" maxFractionDigits="2"/></td>
                                        <td><g:formatNumber number="${bill.amountReceivable}" type="currency"/></td>
                                        <td><g:formatNumber number="${bill.discount}" type="currency"/></td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                </div>
            </g:form>
        </div>
        <div id="show-business" class="content scaffold-show" role="main">
            <h1>Dados da Empresa</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="business" />
            <g:form resource="${this.business}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.business}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
