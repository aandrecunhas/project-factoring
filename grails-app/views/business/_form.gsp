<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default panel-fixed fadeInUp">
            <div class="panel-body">
                <div class="form-group">
                    <label for="name">Nome</label>
                    <g:textField name="name" class="form-control"
                                 value="${business.name}"
                                 required=""  autocomplete="off"/>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <g:textField name="email" class="form-control"
                                 value="${business.email}"
                                 required=""  autocomplete="off"/>
                </div>
                <div class="form-group">
                    <label for="enterpriseName">Nome da Empresa</label>
                    <g:textField name="enterpriseName" class="form-control"
                                 value="${business.enterpriseName}"
                                 required=""  autocomplete="off"/>
                </div>
                <div class="form-group">
                    <label for="monthlyRevenue">Faturamento Mensal</label>
                    <g:field name="monthlyRevenue" class="form-control money" value="${g.formatNumber(number: business?.monthlyRevenue ?: 0.0, format: '#,##0.00')}" required=""></g:field>
                </div>
            </div>
        </div>
    </div>
</div>