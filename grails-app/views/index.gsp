<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
    <content tag="nav">
    </content>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Welcome to Factoring</h1>

            <p>
                Bem-vindo ao sistema de Factoring! Aqui você pode estimar a sua taxa de antecipação de recebíveis. Clique no botão abaixo, preencha seus dados e cálcule quanto irá receber título a título.
            </p>
        </section>

        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-5">
                    <a href="${createLink(controller: 'business', action: 'index')}" class="btn btn-success">Cadastre sua empresa</a>
                </div>
            </div>
        </div>

    </div>
</body>
</html>
