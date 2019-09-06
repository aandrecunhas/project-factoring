<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'business.label', default: 'Empresa')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-business" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<br/>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-business" class="content scaffold-list" role="main">
    <h1>Listagem de empresas</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table class="table table-striped">
        <thead>
        <tr>
           <th>Nome</th>
           <th>Email</th>
           <th>Nome da Empresa</th>
           <th>Faturamento Mensal</th>
           <th>Taxa Base *</th>
           <th></th>
        </tr>
        </thead>
        <tbody>
        <g:if test="${businessList}">
            <g:each var="business" in="${businessList}">
                <tr>
                    <td>${business.name}</td>
                    <td>${business.email}</td>
                    <td>${business.enterpriseName}</td>
                    <td><g:formatNumber number="${business.monthlyRevenue}" type="currency"/></td>
                    <td><g:formatNumber number="${business.baseRate.percentage / 100}" type="percent" maxFractionDigits="2"/></td>
                    <td><a href="${createLink(controller: 'business', action: 'show', id:business.id)}" type="button" class="btn btn-success">Calcule sua taxa</a></td>
                </tr>
            </g:each>
        </g:if>
        <g:else>
            <tr>
                <td colspan="8">Nenhum registro encontrado</td>
            </tr>
        </g:else>
        </tbody>
    </table>

    <g:if test="${businessCount > params.max}">
        <div class="pagination">
            <g:paginate total="${businessCount ?: 0}" />
        </div>
    </g:if>
    <p>* A taxa final dependende da data de vencimento do seu título! Utilize o link "calcule sua taxa" para encontrar sua taxa final.</p>
</div>
</body>
</html>